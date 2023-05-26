package views;

import engine.Game;

public class StatsManager {

    public static void updateSelectedStats(){
        Main.checkGameOver();
        String s1 = "Name: " + Main.selectedChar.getName() + "\nType: " + Main.selectedChar.getClass().getSimpleName() + "\nHP: " +
        Main.selectedChar.getCurrentHp() + "/" + Main.selectedChar.getMaxHp() + "\nActions: " + Main.selectedChar.getActionsAvailable() + "/" +
        Main.selectedChar.getMaxActions();
        
        String s2 = "\n  Atk: " + Main.selectedChar.getAttackDmg() + "\n  Vaccines: " +  Main.selectedChar.getVaccineInventory().size() + 
        "\n  Supplies: " + Main.selectedChar.getSupplyInventory().size();

        String name = Main.selectedChar.getName();
        int h = (int)(190 * Main.factor);

        if(name.equals("Joel Miller")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[0]);
            Main.charImgViewsBorder[0].setFitHeight(h);
        }
        else if(name.equals("Ellie Williams")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[1]);
            Main.charImgViewsBorder[1].setFitHeight(h);
        }
        else if(name.equals("Tess")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[2]);
            Main.charImgViewsBorder[2].setFitHeight(h);
        }
        else if(name.equals("Riley Abel")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[3]);
            Main.charImgViewsBorder[3].setFitHeight(h);
        }
        else if(name.equals("Tommy Miller")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[4]);
            Main.charImgViewsBorder[4].setFitHeight(h);
        }
        else if(name.equals("Bill")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[5]);
            Main.charImgViewsBorder[5].setFitHeight(h);
        }
        else if(name.equals("David")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[6]);
            Main.charImgViewsBorder[6].setFitHeight(h);
        }
        else if(name.equals("Henry Burell")){
            Main.selectedCharImg.setGraphic(Main.charImgViewsBorder[7]);
            Main.charImgViewsBorder[7].setFitHeight(h);
        }
        
        Main.selectedCharStats1.setText(s1);
        Main.selectedCharStats2.setText(s2);
        
    }

    public static void updateOtherStats(){
        int otherCharsNum = -1;
        int h = (int)(100 * Main.factor);
        
        for(int i = 0; i < Game.heroes.size(); i++){
            if(!(Game.heroes.get(i).equals(Main.selectedChar))){
                otherCharsNum++;
                
                String s = Game.heroes.get(i).getName() + "\n" + Game.heroes.get(i).getClass().getSimpleName() + "\n" +
                Game.heroes.get(i).getCurrentHp() + ", " + Game.heroes.get(i).getActionsAvailable() + ", " + Game.heroes.get(i).getAttackDmg();

                String name = Game.heroes.get(i).getName();

                if(name.equals("Joel Miller")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[0]);
                    Main.charImgViewsBorder[0].setFitHeight(h);
                }
                else if(name.equals("Ellie Williams")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[1]);
                    Main.charImgViewsBorder[1].setFitHeight(h);
                }
                else if(name.equals("Tess")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[2]);
                    Main.charImgViewsBorder[2].setFitHeight(h);
                }
                else if(name.equals("Riley Abel")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[3]);
                    Main.charImgViewsBorder[3].setFitHeight(h);
                }
                else if(name.equals("Tommy Miller")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[4]);
                    Main.charImgViewsBorder[4].setFitHeight(h);
                }
                else if(name.equals("Bill")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[5]);
                    Main.charImgViewsBorder[5].setFitHeight(h);
                }
                else if(name.equals("David")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[6]);
                    Main.charImgViewsBorder[6].setFitHeight(h);
                }
                else if(name.equals("Henry Burell")){
                    Main.otherCharImgs[otherCharsNum].setGraphic(Main.charImgViewsBorder[7]);
                    Main.charImgViewsBorder[7].setFitHeight(h);
                }

                Main.otherCharTexts[otherCharsNum].setText(s);
            }
        }
        
    }

    public static void clearStats(){
        Main.selectedCharStats1.setText("");
        Main.selectedCharStats2.setText("");
        Main.selectedCharImg.setGraphic(null);

        for(int i = 0; i < Main.otherCharTexts.length; i++){
            Main.otherCharTexts[i].setText("");
            Main.otherCharImgs[i].setGraphic(null);
        }
    }
}