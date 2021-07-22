package it.unicam.cs.ids.c3.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.service.CommercianteService;
import it.unicam.cs.ids.c3.backend.service.DescrizioneProdottoService;
import it.unicam.cs.ids.c3.backend.service.NegozioService;

@Route(value="negozio", layout = MainLayout.class)
@PageTitle("Negozio")
public class NegozioView extends VerticalLayout {
    NegozioService negozioService;
    CommercianteService commercianteService;
    DescrizioneProdottoService descrizioneProdottoService;
    private Grid<Prodotto> grid = new Grid<>(Prodotto.class);
    private Select<Commerciante> commercianteSelect = new Select<>();
    private Select<DescrizioneProdotto> descrizioneProdottoSelect = new Select<>();
    private IntegerField quantity = new IntegerField();
    Button addButton = new Button(new Icon(VaadinIcon.ADD_DOCK));

    public NegozioView(NegozioService negozioService, CommercianteService commercianteService, DescrizioneProdottoService descrizioneProdottoService) {
        this.negozioService = negozioService;
        this.commercianteService = commercianteService;
        this.descrizioneProdottoService = descrizioneProdottoService;
        addClassName("list-view");
        setSizeFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout(commercianteSelect,descrizioneProdottoSelect,quantity,addButton);
        addButton.addClickListener(e -> addProduct(commercianteSelect.getValue(),descrizioneProdottoSelect.getValue(),quantity.getValue()));
        setComponents();
        configureGrid();
        add(horizontalLayout,grid);
       // updateList();
    }

    private void updateList() {grid.setItems(negozioService.search(commercianteSelect.getValue().getNomeCommerciante()).get(0).getVetrina());
    }


    private void setComponents(){
        commercianteSelect.setItemLabelGenerator(Commerciante::getNomeCommerciante);
        commercianteSelect.setItems(commercianteService.findAll());
        descrizioneProdottoSelect.setItemLabelGenerator(DescrizioneProdotto::getNomeProdotto);
       descrizioneProdottoSelect.setItems(descrizioneProdottoService.findAll());
       quantity.setHasControls(true);
       quantity.setMin(1);

    }

    private void addProduct(Commerciante commercianteSelectValue, DescrizioneProdotto value, Integer value1) {
        Notification notification = new Notification("Negozio non trovato", 3000);
        notification.open();
        Prodotto prodotto = new Prodotto(value,value1);
        negozioService.addProduct(commercianteSelectValue.getNomeCommerciante(),prodotto);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("vetrina-grid");
        grid.setSizeFull();
        grid.setColumns("descrizione.codiceProdotto", "descrizione.nomeProdotto", "descrizione.descrizioneProdotto","quantita");
    }
}
