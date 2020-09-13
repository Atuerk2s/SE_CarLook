package org.example.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.example.gui.components.TopPanel;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.Auto;
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
    private Auto selected = null;

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
        //final Label label = new Label(vorname + ", gib einen Ort ein:");
        final Label label = new Label("Suchbegriff eingeben:");
        Button suchenButton = new Button("Suchen", VaadinIcons.SEARCH);
        Button reservierenButton = new Button("Reservieren", VaadinIcons.BOOK);
        final TextField textinput = new TextField();



        horizon.addComponents(label, textinput, suchenButton);
        addComponent(horizon);

        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        Grid<Auto> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        // Die aktuell angewählte Zeile in der Tabelle (aka dem Grid)
        SingleSelect<Auto> selection = grid.asSingleSelect();

        // Der Event Listener für den Grid
        grid.addSelectionListener(event -> {

            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
            MainView.this.selected = selection.getValue();

        });

        // Event Listener für den Suchen Button
        suchenButton.addClickListener(e -> {

            //Fange Eingabe ab
            String suchbegriff = textinput.getValue();
            List<Auto> liste = HotelSearch.getInstance().getAutoByMarke(suchbegriff);
            //!! List<Auto> liste = HotelSearch.getInstance().getHotelByOrt(suchbegriff);


            //Falls nichts eingegeben wurde
            if (suchbegriff.equals("")) {
                Notification.show(null, "Bitte Suchbegriff eingeben!", Notification.Type.WARNING_MESSAGE);
            }
            anzahl += 1; //Wie oft wurde gesucht

            //erstmal alles löschen
            grid.removeAllColumns();

            grid.setCaption("Treffer für " + suchbegriff + " (Anzahl der Suchen: " + MainView.this.anzahl + ")" +
                    ((MyUI) UI.getCurrent()).getUser().getName());

            //Liste mit passenden Hotels einfügen
            grid.setItems(liste);

           //Columns definieren
            grid.addColumn(Auto::getId).setCaption("ID");
            grid.addColumn(Auto::getMarke).setCaption("Marke");
            grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
            grid.addColumn(Auto::getBeschreibung).setCaption("Beschreibung");


        });


        reservierenButton.addClickListener(e -> {
            if (MainView.this.selected == null){
            } else {
                //BookingWindow window = new BookingWindow(MainView.this.selected);
                //UI.getCurrent().addWindow(window);
            }
        });


        // Grid und Buchen Button richtig anordnen
        addComponent(grid);
        addComponent(reservierenButton);
        setComponentAlignment(reservierenButton, Alignment.MIDDLE_CENTER);

    }

}
