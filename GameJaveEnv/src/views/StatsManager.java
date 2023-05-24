package views;

import javafx.scene.control.Label;

public class StatsManager {
    public static void updateSelectedStats(){
        
        String s1 = "Name: " + Main.selectedChar.getName() + "\nType: " + Main.selectedChar.getClass().getSimpleName() + "\nHP: " +
        Main.selectedChar.getCurrentHp() + "/" + Main.selectedChar.getMaxHp() + "\nActions: " + Main.selectedChar.getActionsAvailable() + "/" +
        Main.selectedChar.getMaxActions();
        
        String s2 = "\n  Atk: " + Main.selectedChar.getAttackDmg() + "\n  Vaccines: " +  Main.selectedChar.getVaccineInventory().size() + 
        "\n  Supplies: " + Main.selectedChar.getSupplyInventory().size();
        
        Main.selectedCharStats1.setText(s1);
        Main.selectedCharStats2.setText(s2);
        
    }
}