package Combat;

import Combat.Unit.Weapon;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final int MAX_IN_HAND = 2;


    private ArrayList<WeaponActionCard> deck = new ArrayList<>();
    private WeaponActionCard[] hands_card = new WeaponActionCard[MAX_IN_HAND];

    private Random random = new Random();
    private int card_in_hand;

    private Button closeButton;
    public Button getCloseButton() {
        return closeButton;
    }

    private Weapon weapon;

    public Deck(ArrayList<WeaponActionCard> deck, Weapon weapon) {
        this.weapon=weapon;
        this.deck = deck;
        card_in_hand = 2;
        //XML pour avoir toutes les cartes dans le deck

        //Initialiser deux cartes dans la main
        hands_card[0] = deck.get(random.nextInt(deck.size()));
        hands_card[1] = deck.get(random.nextInt(deck.size()));


        deckPane.setLayoutX(620);
        deckPane.setLayoutY(300);
        deckPane.getStyleClass().clear();
        deckPane.getStyleClass().add("deckPane");
        col.setPrefWidth(130);
        row.setPrefHeight(160);
        deckPane.getColumnConstraints().add(col);
        deckPane.getColumnConstraints().add(col);
        deckPane.getRowConstraints().add(row);
        RadioButton cardButton0 = new RadioButton(hands_card[0].getNameActionCard());
        cardButton0.setPrefSize(160,130);
        cardButton0.getStyleClass().clear();
        cardButton0.getStyleClass().add("button");
        cardButton0.setToggleGroup(cardToggleGroup);
        RadioButton cardButton1 = new RadioButton(hands_card[1].getNameActionCard());
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
                    if (hands_card[indexSelected].getType().equals("buff")) {
                        weapon.weaponBuff(hands_card[indexSelected]);
                        deckPane.getChildren().remove(buttonCardSelected);
                    }
                    else if (hands_card[indexSelected].getType().equals("attack")) {
                        if (BombingCombatManager.getInstance().getSelectedEnnemyShip()!=null) {
                            weapon.weaponAttack(BombingCombatManager.getInstance().getSelectedEnnemyShip(), hands_card[indexSelected]);
                            deckPane.getChildren().remove(buttonCardSelected);
                        }
                    }
                }
                actionToggleGroup.selectToggle(null);
            }
        });
    }

    int indexSelected=-1;
    RadioButton buttonCardSelected;

    private final ToggleGroup cardToggleGroup = new ToggleGroup();
    private final ToggleGroup actionToggleGroup = new ToggleGroup();

    public ArrayList<WeaponActionCard> getDeck () {
        return deck;
    }

    public WeaponActionCard[] getHands_card() {
        return hands_card;
    }

    public void newTurn() {
        while(card_in_hand < MAX_IN_HAND){
            if(hands_card[0]==null){
                hands_card[0] = deck.get(random.nextInt(deck.size()));
                card_in_hand++;
            }
            else {
                hands_card[1] = deck.get(random.nextInt(deck.size()));
                card_in_hand++;
            }
        }
    }

    public void discard(int index) {
        hands_card[index] = null;
        card_in_hand--;
    }

    GridPane deckPane = new GridPane();
    ColumnConstraints col = new ColumnConstraints();
    RowConstraints row = new RowConstraints();
    public GridPane getDeckPane() {
        return deckPane;
    }

}
