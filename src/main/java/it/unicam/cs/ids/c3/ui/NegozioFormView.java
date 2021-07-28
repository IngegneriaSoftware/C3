package it.unicam.cs.ids.c3.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.service.CommercianteService;
import it.unicam.cs.ids.c3.backend.service.DescrizioneProdottoService;
import it.unicam.cs.ids.c3.backend.service.NegozioService;
import it.unicam.cs.ids.c3.backend.service.ProdottoService;

import javax.swing.*;
import java.util.ArrayList;

@Route(value = "negozioform", layout = MainLayout.class)
@PageTitle("Crea negozio")
public class NegozioFormView extends HorizontalLayout {

    private ArrayList<DescrizioneProdotto> productsDescriptions = new ArrayList<>();
    private ArrayList<Prodotto> products = new ArrayList<>();
    private TextField nome = new TextField();
    private TextField indirizzo = new TextField();
    private Select<Commerciante> commercianteSelect = new Select<>();
    private Select<DescrizioneProdotto> prodottoSelect = new Select<>();
    private Button addProductButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
    private Button saveButton = new Button("Save");
    private Grid<Prodotto> prodotti = new Grid<>(Prodotto.class);
    private CommercianteService commercianteService;
    private ProdottoService prodottoService;
    private DescrizioneProdottoService descrizioneProdottoService;
    private NegozioService negozioService;

    public NegozioFormView(CommercianteService commercianteService, ProdottoService prodottoService, DescrizioneProdottoService descrizioneProdottoService,NegozioService negozioService) {
        this.commercianteService = commercianteService;
        this.prodottoService = prodottoService;
        this.descrizioneProdottoService = descrizioneProdottoService;
        this.negozioService= negozioService;
        addProductButton.addClickListener(e -> addToGrid());
        saveButton.addClickListener(e -> createNegozio());
        this.setSpacing(false);
        add(setComponents(), configureGrid());
    }

    private void changeQty(int qty) {
        prodotti.getSelectionModel().getFirstSelectedItem().get().setQuantita(qty);
    }

    private void createNegozio() {
        Negozio negozio = new Negozio();
        negozioService.save(negozio);
        for(int  i=0;i<products.size();i++){
            products.get(i).setNegozio(negozio);
            prodottoService.save(products.get(i));
        }
        negozio.setNomeNegozio(nome.getValue());
        negozio.setCommerciante(commercianteSelect.getValue());
        negozio.setIndirizzo(indirizzo.getValue());
        negozio.setVetrina(products);
        negozioService.save(negozio);

    }

    private void addToGrid() {
        productsDescriptions.add(prodottoSelect.getValue());
        products.add(new Prodotto(prodottoSelect.getValue(), 1));
        prodotti.setItems(products);
        prodotti.getDataProvider().refreshAll();
    }

    private VerticalLayout setComponents() {
        H2 title = new H2("Crea negozio");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nome.setLabel("Nome");
        nome.setPlaceholder("Inserisci nome...");
        indirizzo.setLabel("Indirizzo");
        indirizzo.setPlaceholder("Inserisci indirizzo...");
        commercianteSelect.setLabel("Commerciante");
        commercianteSelect.setItemLabelGenerator(Commerciante::getNomeCommerciante);
        commercianteSelect.setItems(commercianteService.findAll());

        VerticalLayout layout = new VerticalLayout();
        layout.add(title, nome, indirizzo, commercianteSelect);
        return layout;
    }

    private VerticalLayout configureGrid() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(prodottoSelect, addProductButton);
        horizontalLayout.setAlignItems(Alignment.BASELINE);

        VerticalLayout layout = new VerticalLayout();
        prodottoSelect.setLabel("Prodotti");
        prodottoSelect.setItemLabelGenerator(DescrizioneProdotto::getNomeProdotto);
        prodottoSelect.setItems(descrizioneProdottoService.findAll());
        prodotti.addClassName("vetrina-grid");
        prodotti.setSizeFull();
        prodotti.setColumns("descrizione.codiceProdotto", "descrizione.nomeProdotto", "descrizione.descrizioneProdotto");
        prodotti.addColumn(new ComponentRenderer<>(item -> addIntField())).setHeader("Quantity");
        layout.add(horizontalLayout, prodotti, saveButton);
        return layout;
    }

    private Component addIntField() {
        IntegerField field = new IntegerField();
        field.setHasControls(true);
        field.setMin(1);
        field.setValue(1);
        field.addValueChangeListener(e -> changeQty(e.getValue()));
        return field;
    }
}