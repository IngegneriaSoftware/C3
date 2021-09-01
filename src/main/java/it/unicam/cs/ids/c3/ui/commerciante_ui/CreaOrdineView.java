package it.unicam.cs.ids.c3.ui.commerciante_ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.*;
import it.unicam.cs.ids.c3.backend.service.ClienteService;
import it.unicam.cs.ids.c3.backend.service.NegozioService;
import it.unicam.cs.ids.c3.backend.service.OrdineService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "creaordine", layout = CommercianteLayout.class)
@PageTitle("Crea ordine")
public class CreaOrdineView extends HorizontalLayout {

   private Grid<Cliente> clienteGrid = new Grid<>(Cliente.class);
   private Grid<Prodotto>  prodottoGrid = new Grid<>(Prodotto.class);
   private TextField filterText = new TextField();
   private ClienteService clienteService;
   private NegozioService negozioService;
   private OrdineService ordineService;
   private Select<Negozio> negozioSelect = new Select<>();
   private Select<StatusOrdine> statusOrdineSelect = new Select<>();
   private Select<Prodotto> prodottoSelect = new Select<>();
   private TextField puntoRitiro = new TextField();
   private Button saveButton = new Button("Save");
   private IntegerField qty = new IntegerField();
   private ArrayList<Prodotto> productList = new ArrayList<>();

   public CreaOrdineView(ClienteService clienteService,NegozioService negozioService,OrdineService ordineService) {
      this.clienteService = clienteService;
      this.negozioService= negozioService;
      this.ordineService= ordineService;
      configureGrid();
      add(setComponents(),setProductsGrid());
   }

   private VerticalLayout setProductsGrid(){
      VerticalLayout layout = new VerticalLayout();
      HorizontalLayout horizontalLayout = new HorizontalLayout();
     prodottoSelect.setLabel("Prodotti");
     prodottoSelect.setItemLabelGenerator(Prodotto::getNomeProdotto);
      Button btn = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
      btn.addClickListener(event -> {
        Prodotto prodotto = prodottoSelect.getValue();
        prodotto.setQuantita(prodotto.getQuantita()-qty.getValue());
          Prodotto p = null;
          try {
              p = prodotto.clone();
          } catch (CloneNotSupportedException e) {
              e.printStackTrace();
          }
          p.setQuantita(qty.getValue());
        productList.add(p);
        prodottoGrid.setItems(productList);


      });
     prodottoSelect.addToPrefix(btn);
     qty.setHasControls(true);
     qty.setMin(1);
     if(prodottoSelect.isEmpty()==false || prodottoSelect.getValue()!=null){qty.setMax(prodottoSelect.getValue().getQuantita());}
     qty.setValue(1);
     qty.setLabel("Quantità");
     prodottoGrid.setColumns("descrizione.nomeProdotto");
     prodottoGrid.addColumn(new ComponentRenderer<>(item -> addTextField(item))).setHeader("Quantita").setKey("quantita");
     saveButton.addClickListener(event -> createOrder() );
    horizontalLayout.add(prodottoSelect,qty);
     layout.add(horizontalLayout,prodottoGrid,saveButton);
      return layout;
   }



   private void createOrder() {
       Negozio negozio = negozioSelect.getValue();
       negozioService.save(negozio);
       Cliente cliente =  clienteGrid.getSelectionModel().getFirstSelectedItem().get();
      Ordine ordine = new Ordine(statusOrdineSelect.getValue(),productList,puntoRitiro.getValue(),cliente);
       ordineService.save(ordine);
       Notification notification = new Notification("Ordine creato");
       notification .setDuration(3000);
       notification.open();
   }

   private Component addTextField(Prodotto item) {
      IntegerField field = new IntegerField();
      field.setEnabled(false);
      field.setValue(item.getQuantita());
      //field.setHasControls(true);
      return field;
   }

   private VerticalLayout setComponents(){
      VerticalLayout layout = new VerticalLayout();
      filterText.setLabel("Cliente");
      filterText.setSuffixComponent(new Icon(VaadinIcon.SEARCH));
      filterText.setPlaceholder("Cerca per nome...");
      filterText.setClearButtonVisible(true);
      filterText.setValueChangeMode(ValueChangeMode.LAZY);
      filterText.addValueChangeListener(e -> updateList(e.getValue()));
      clienteGrid.setHeight("250px");
      clienteGrid.setWidth("350px");
      negozioSelect.setLabel("Negozio");
      negozioSelect.setItemLabelGenerator(Negozio::getNomeNegozio);
      negozioSelect.setItems(negozioService.findAll());
      negozioSelect.addValueChangeListener(event -> {prodottoSelect.setItems(negozioSelect.getValue().getVetrina());});
      statusOrdineSelect.setLabel("Status Ordine");
      statusOrdineSelect.setItems(StatusOrdine.values());
      puntoRitiro.setLabel("Punto Ritiro");
      layout.add(filterText,clienteGrid,negozioSelect,statusOrdineSelect,puntoRitiro);
      return layout;
   }

   private void updateList(String value) {
      clienteGrid.setItems(clienteService.findAll(value));
   }

   private void configureGrid(){
      clienteGrid.setColumns("nomeCliente");
      clienteGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
              GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
      clienteGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
   }
}
