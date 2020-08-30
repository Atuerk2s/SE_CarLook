package org.example.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.example.gui.components.TopPanel;
import org.example.gui.ui.MyUI;
import org.example.gui.windows.BookingWindow;
import org.example.model.objects.dto.Hotel;
import org.example.model.objects.dto.User;
import org.example.process.control.HotelSearch;
import org.example.services.util.Views;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){

        //User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        //falls User nicht eingeloggt, kann er Main nicht accessen und wird auf Loginpage delegiert
        if(user == null){
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }else {
            this.setUp();
        }
    }

    private int anzahl = 0;
    private Hotel selected = null;

    public void setUp(){

        //Mir wurde in Aldas version keine Linie angezeigt, deshalb Label explizit erstellt mit Width Änderung
        this.addComponent(new TopPanel());
        Label hr = new Label("<hr />", ContentMode.HTML);
        hr.setWidth(100f, Unit.PERCENTAGE);
        this.addComponent(hr);

        setMargin(true);



        final HorizontalLayout horizon = new HorizontalLayout();

        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if(user != null){
            vorname = user.getName();
        }

        //Mit Personalisierung
        final Label label = new Label(vorname + ", gib einen Ort ein:");
        Button suchenButton = new Button("Suchen", VaadinIcons.SEARCH);
        Button buchenButton = new Button("Buchen", VaadinIcons.BOOK);
        final TextField textinput = new TextField();



        horizon.addComponents(label, textinput, suchenButton);
        addComponent(horizon);

        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        Grid<Hotel> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        // Die aktuell angewählte Zeile in der Tabelle (aka dem Grid)
        SingleSelect<Hotel> selection = grid.asSingleSelect();

        // Der Event Listener für den Grid
        grid.addSelectionListener(event -> {

            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
            MainView.this.selected = selection.getValue();

        });

        // Event Listener für den Suchen Button
        suchenButton.addClickListener(e -> {

            //Fange Eingabe ab
            String ort = textinput.getValue();
            List<Hotel> liste = HotelSearch.getInstance().getHotelByOrt(ort);

            //Falls nichts eingegeben wurde
            if (ort.equals("")) {
                Notification.show(null, "Bitte Ort eingeben!", Notification.Type.WARNING_MESSAGE);
            }
            anzahl += 1; //Wie oft wurde gesucht

            //erstmal alles löschen
            grid.removeAllColumns();

            grid.setCaption("Treffer für " + ort + " (Anzahl der Suchen: " + MainView.this.anzahl + ")" +
                    ((MyUI) UI.getCurrent()).getUser().getName());

            //Liste mit passenden Hotels einfügen
            grid.setItems(liste);

            // Columns definieren
            grid.addColumn(Hotel::getName).setCaption("Name");
            grid.addColumn(Hotel::getId).setCaption("ID");
            grid.addColumn(Hotel::getOrt).setCaption("Ort");
            grid.addColumn(Hotel::getDescription).setCaption("Beschreibung");
        });


        buchenButton.addClickListener(e -> {
            if (MainView.this.selected == null){
            } else {
                BookingWindow window = new BookingWindow(MainView.this.selected);
                UI.getCurrent().addWindow(window);
            }
        });


        // Grid und Buchen Button richtig anordnen
        addComponent(grid);
        addComponent(buchenButton);
        setComponentAlignment(buchenButton, Alignment.MIDDLE_CENTER);

    }

}
