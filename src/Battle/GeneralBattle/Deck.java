package Battle.GeneralBattle;

import Battle.Unit.Weapon;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implement the deck constituted of the action cards of an unit.
 */
public class Deck {
    private final int MAX_IN_HAND = 2;


    /**
     * Deck of available action cards of the weapon.
     */
    private final ArrayList<WeaponActionCard> actionCardsList;
    /**
     * Cards from the deck in the hand of the player.
     */
    private final WeaponActionCard[] handsCard = new WeaponActionCard[MAX_IN_HAND];
    /**
     * Number of cards in the hand of the player for this deck's weapon.
     */
    private int numberCardsInHand;

    /**
     * Button closing the pane displaying drown cards of this deck's weapon.
     */
    private final Button closeButton;

    /**
     * Returns the button closing this deck's pane.
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     * Creates a new instance of deck and sets the properties of the pane and buttons of the deck.
     * It also initialises the handsCard by drawing two random cards.
     * @param actionCardsList the list of the available action cards
     * @param weapon the weapon to which belongs the deck
     */
    public Deck(ArrayList<WeaponActionCard> actionCardsList, Weapon weapon) {

        ColumnConstraints col = new ColumnConstraints();
        RowConstraints row = new RowConstraints();
        this.actionCardsList = actionCardsList;
        numberCardsInHand = 2;
        //XML pour avoir toutes les cartes dans le deck

        //Initialiser deux cartes dans la main
        Random random = new Random();
        handsCard[0] = actionCardsList.get(random.nextInt(actionCardsList.size()));
        handsCard[1] = actionCardsList.get(random.nextInt(actionCardsList.size()));


        ToggleGroup actionToggleGroup = new ToggleGroup();

        deckPane.setLayoutX(620);
        deckPane.setLayoutY(300);
        deckPane.getStyleClass().clear();
        deckPane.getStyleClass().add("deckPane");
        col.setPrefWidth(130);
        row.setPrefHeight(160);
        deckPane.getColumnConstraints().add(col);
        deckPane.getColumnConstraints().add(col);
        deckPane.getRowConstraints().add(row);
        RadioButton cardButton0 = new RadioButton(handsCard[0].getNameActionCard());
        cardButton0.setPrefSize(160,130);
        cardButton0.getStyleClass().clear();
        cardButton0.getStyleClass().add("button");
        ToggleGroup cardToggleGroup = new ToggleGroup();
        cardButton0.setToggleGroup(cardToggleGroup);
        RadioButton cardButton1 = new RadioButton(handsCard[1].getNameActionCard());
        cardButton1.setPrefSize(160,130);
        cardButton1.getStyleClass().clear();
        cardButton1.setToggleGroup(cardToggleGroup);
        cardButton1.getStyleClass().add("button");
        closeButton = new Button("Close");
        deckPane.add(closeButton,0,2);
        deckPane.add(cardButton0,0,0);
        deckPane.add(cardButton1,1,0);
        cardButton0.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                indexSelected=0;
                buttonCardSelected=cardButton0;
            }
            else {
                indexSelected=-1;
                buttonCardSelected=null;
            }
        });
        cardButton1.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                indexSelected=1;
                buttonCardSelected=cardButton1;
            }
            else {
                indexSelected=-1;
                buttonCardSelected=null;
            }
        });

        RadioButton discardButton = new RadioButton("Discard");
        discardButton.getStyleClass().clear();
        discardButton.getStyleClass().add("button");
        discardButton.setToggleGroup(actionToggleGroup);
        deckPane.add(discardButton,0,1);
        discardButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                if (indexSelected!=-1) {
                    discard(indexSelected);
                    deckPane.getChildren().remove(buttonCardSelected);
                }
                actionToggleGroup.selectToggle(null);
            }
        });

        RadioButton actionButton = new RadioButton("Action");
        actionButton.getStyleClass().clear();
        actionButton.setToggleGroup(actionToggleGroup);
        actionButton.getStyleClass().add("button");
        deckPane.add(actionButton,1,1);
        actionButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                if (indexSelected != -1) {
                    if (handsCard[indexSelected].getType().equals("buff")) {
                        weapon.weaponBuff(handsCard[indexSelected]);
                        deckPane.getChildren().remove(buttonCardSelected);
                    }
                    else if (handsCard[indexSelected].getType().equals("attack")) {
                        if (BombingCombatManager.getInstance().getSelectedEnemyShip()!=null) {
                            weapon.weaponAttack(BombingCombatManager.getInstance().getSelectedEnemyShip(), handsCard[indexSelected]);
                            deckPane.getChildren().remove(buttonCardSelected);
                        }
                    }
                }
                actionToggleGroup.selectToggle(null);
            }
        });
    }

    /**
     * Index of the selected card.
     */
    private int indexSelected=-1;
    /**
     * RadioButton of the selected card.
     */
    private RadioButton buttonCardSelected;

    /**
     * Redraws the cards until the player has 2 cards in hands for this weapon.
     */
    public void newTurn() {
        Random random = new Random();
        while(numberCardsInHand < MAX_IN_HAND){
            if(handsCard[0]==null){
                handsCard[0] = actionCardsList.get(random.nextInt(actionCardsList.size()));
                numberCardsInHand++;
            }
            if(handsCard[1]==null) {
                handsCard[1] = actionCardsList.get(random.nextInt(actionCardsList.size()));
                numberCardsInHand++;
            }
        }
    }

    /**
     * Discards the selected card.
     * @param index the index of the discarded card
     */
    public void discard(int index) {
        handsCard[index] = null;
        numberCardsInHand--;
    }

    /**
     * Pane displaying the cards in hands, as well as the action, discard and close buttons.
     */
    private final GridPane deckPane = new GridPane();

    /**
     * Returns the pane of the deck.
     */
    public GridPane getDeckPane() {
        return deckPane;
    }

}
