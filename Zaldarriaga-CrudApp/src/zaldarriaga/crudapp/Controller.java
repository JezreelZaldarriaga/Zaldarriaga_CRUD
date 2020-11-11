package zaldarriaga.crudapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    View_login cv;
    View_bank vb;
    public Controller(View_login cv, View_bank vb){
        this.cv = cv;
        this.vb = vb;
        cv.allListener(new ActionBtn());
        vb.allListener(new ActionBtn());
    }

    class ActionBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String identification = cv.idno.getText();
            
            
            if (e.getSource() == cv.btnEnter){
                if(cv.name.getText().equals("") | cv.idno.getText().equals("")){
                    cv.notif.setText("Can't Login! Please enter name or ID number first!");
                }
                else{
                String username =cv.name.getText();
                
                String doublecheck = Model.check_user(identification, username);
                if (doublecheck!= null){
                    
                    vb.name.setText(username);
                    vb.setTitle("Admin - Mini-Bank");
                    vb.setVisible(true);
                }
                else{
                    vb.name.setText(username);
                    vb.setTitle("Admin - Mini-Bank");
                    vb.setVisible(true);
                }
                }
            }
            if (e.getSource() == vb.btnWithdraw){
                       if (vb.cur_ballance.getText().equals("") | vb.cur_ballance.getText() == null | vb.withdraw.getText().equals("") | vb.deposit.getText().equals("") ){
                          vb.deposit.setText("0"); 
                          vb.withdraw.setText("0"); 
                       }
                       else{
                           int current = Integer.parseInt(vb.cur_ballance.getText());
                           if(current == 0){
                               vb.cur_ballance.setText("0");
                           }else{
                                String num1 = vb.cur_ballance.getText();
                                String num2 = vb.withdraw.getText();
                                String ans = Integer.toString(Model.subtract(num1, num2));
                                int checker = Integer.parseInt(ans);
                                if(checker < 0){
                                    vb.cur_ballance.setText(num1);
                                    vb.withdraw.setText("");
                                    vb.notif.setText("Insufficient Balance can't withdraw!");
                                }
                                else{
                                    Model.update_data(ans,identification);
                                    vb.cur_ballance.setText(ans);
                                    vb.withdraw.setText("");
                                }
                                
                           }
                            
                           
                       }
            }
            if (e.getSource() == vb.btnDeposit){
                       if (vb.cur_ballance.getText().equals("") | vb.cur_ballance.getText() == null| vb.withdraw.getText().equals("") | vb.deposit.getText().equals("") ){
                          vb.deposit.setText("0"); 
                          vb.withdraw.setText("0"); 
                       }
                       else{
                           String num1 = vb.cur_ballance.getText();
                           String num2 = vb.deposit.getText();
                           String sum = Integer.toString(Model.add(num1, num2));
                           Model.update_data(sum,identification);
                           vb.cur_ballance.setText(sum); 
                           vb.deposit.setText("");
                           
                       }
            }
            
            if (e.getSource() == vb.btncheck){
                String x = Model.check_balance(identification);
                vb.cur_ballance.setText(x);
                vb.btnDeposit.setEnabled(true);
                vb.btnWithdraw.setEnabled(true);
                //vb.btnConfirm.setEnabled(true);
                vb.btnCoffee.setEnabled(true);
                vb.btnMilk.setEnabled(true);
                vb.btnChoco.setEnabled(true);
                vb.btnTea.setEnabled(true);
            }
            
            
            if (e.getSource() == vb.btnMilk){
                vb.price.setText("10");
                vb.btnConfirm.setEnabled(true);
            }
            if (e.getSource() == vb.btnChoco){
                vb.price.setText("10");
                vb.btnConfirm.setEnabled(true);
            }
            if (e.getSource() == vb.btnCoffee){
                vb.price.setText("15");
                vb.btnConfirm.setEnabled(true);
            }
            if (e.getSource() == vb.btnTea){
                vb.price.setText("8");
                vb.btnConfirm.setEnabled(true);
            }
            if (e.getSource() == vb.btnConfirm){
                //if((vb.price.getText().equals("") | vb.price.getText()==null) | (vb.total.getText().equals("") | vb.total.getText()==null))
                if((vb.total.getText().equals("") | vb.total.getText()==null))
                {
                    if((!vb.price.getText().equals("") | vb.price.getText()!=null)){
                        vb.total.setText(vb.price.getText());
                        vb.btnPay.setEnabled(true);
                    }
                    else{
                        vb.notif.setText("Make sure you confirm order First!");
                    }
                    
                }
                else{
                    String num1 = vb.price.getText();
                    String num2 = vb.total.getText();
                    int answer = Model.add(num1, num2);
      
                    vb.total.setText(""+answer);
                    vb.btnPay.setEnabled(true);
                }
            }
            if(e.getSource() == vb.btnPay){
                String num1 = vb.cur_ballance.getText();
                String num2 = vb.total.getText();
                int money = Model.subtract(num1, num2);
                if (money < 0){
                    vb.notif.setText("Insufficient Balance!!!!");
                }
                else{
                    String rep = Integer.toString(money);
                    vb.cur_ballance.setText(rep);
                    Model.update_data(rep, identification);
                    vb.btnPay.setEnabled(false);
                    vb.btnConfirm.setEnabled(false);
                    vb.price.setText("");
                    vb.total.setText("");
                    
                }
            }
            if (e.getSource() == vb.btnDelete){
                Model.delete_data(identification);
                vb.setVisible(false);
            }
            if (e.getSource() == vb.btnOut){
                vb.setVisible(false);
            }
            //END OF CLOSINGS
        }
    }
   
}
