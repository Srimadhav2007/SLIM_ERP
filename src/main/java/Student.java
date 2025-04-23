import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student{

    static String connectionString = "mongodb+srv://phanisrimadhavmula:<mypassword>>@cluster0.lvomk.mongodb.net/?appName=Cluster0";

                static ServerApi serverApi = ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build();

                static MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .serverApi(serverApi)
                        .applyToConnectionPoolSettings(builder -> 
                                                    builder.maxSize(100)
                                                    .minSize(10)
                                                    .maxWaitTime(1000, java.util.concurrent.TimeUnit.MILLISECONDS))
                        .build();
                        
    static int count;
    static ArrayList<int[][]> counter=new ArrayList<>();
    private String name;
    private int age;
    private String sex;
    private int cur_sem;
    private String program;
    private String branch;
    private String id;
    private String password;
    double[] sem_gpas;
    static void counterset(){
        counter.add(new int[6][8]);
        counter.add(new int[6][4]);
        counter.add(new int[6][4]);
    }
    public static int countDecide(Student st){
        int a = 0;
        int b=0;
        int c=0;
        if(st.getprog().equals("B.Tech")){
            a=0;
            switch (st.getbranch()) {
                case "CS":
                    b=0;
                    break;
                case "EE":
                    b=1;
                    break;
                case "ME":
                    b=2;
                    break;
                case "CH":
                    b=3;
                    break;
                case "CE":
                    b=4;
                    break;
                case "EP":
                    b=5;
                    break;
            
                default:
                    break;
            }
        }
        else if(st.getprog().equals("M.Tech")){
            a=1;
            switch (st.getbranch()) {
                case "CS":
                    b=0;
                    break;
                case "EE":
                    b=1;
                    break;
                case "ME":
                    b=2;
                    break;
                case "CH":
                    b=3;
                    break;
                case "CE":
                    b=4;
                    break;
                case "EP":
                    b=5;
                    break;
            
                default:
                    break;
            }
        }
        else if(st.getprog().equals("M.S")){
            a=2;
            switch (st.getbranch()) {
                case "CS":
                    b=0;
                    break;
                case "EE":
                    b=1;
                    break;
                case "ME":
                    b=2;
                    break;
                case "CH":
                    b=3;
                    break;
                case "CE":
                    b=4;
                    break;
                case "EP":
                    b=5;
                    break;
            
                default:
                    break;
            }
        }
        c=st.getsem()-1;
        counter.get(a)[b][c]++;
        return counter.get(a)[b][c];
    }
    public Student(String n,int a,String s,int c,String p,String b){
        this.name=n;
        this.age=a;
        this.cur_sem=c;
        this.sex=s;
        this.program=p;
        this.branch=b;
        count=countDecide(this);
        this.sem_gpas=new double[cur_sem-1];
        if(!this.program.equals("M.S")){
            this.id=Character.toString(branch.charAt(0))+Character.toString(branch.charAt(1))+(LocalDate.now().getYear()-2000-(cur_sem/2))+Character.toString(program.charAt(0))+String.format("%03d",count);
        }
        else{
            this.id=Character.toString(branch.charAt(0))+Character.toString(branch.charAt(1))+(LocalDate.now().getYear()-2000-(cur_sem/2))+"S"+String.format("%03d",count);
        }
    }
    
    @Override
    public String toString(){
        String info="id: "+this.id+",name: "+this.name+",age: "+this.age+", sex: "+this.sex+", cur_sem: "+this.cur_sem+", program: "+this.program+", branch: "+this.branch+", password: "+this.password+" ";
        for(int i=0;i<sem_gpas.length;i++){
            info+="gpa_sem-"+(i+1)+": "+this.sem_gpas[i]+" ";
        }
        info+='\n';
        return info;
    }
    String getname(){
        return name;
    }
    void setname(String n){
        this.name=n;
    }
    int getage(){
        return age;
    }
    void setage(int a){
        this.age=a;
    }
    int getsem(){
        return cur_sem;
    }
    void setsem(int s){
        this.cur_sem=s;
    }
    String getsex(){
        return sex;
    }
    String getprog(){
        return program;
    }
    String getbranch(){
        return branch;
    }
    String getid(){
        return id;
    }
    void setid(){
        if(!this.program.equals("M.S")){
            this.id=Character.toString(branch.charAt(0))+Character.toString(branch.charAt(1))+(LocalDate.now().getYear()-2000-(cur_sem/2))+Character.toString(program.charAt(0))+String.format("%03d",count);
        }
        else{
            this.id=Character.toString(branch.charAt(0))+Character.toString(branch.charAt(1))+(LocalDate.now().getYear()-2000-(cur_sem/2))+"S"+String.format("%03d",count);
        }
    }
    void setid(String id){
        this.id=id;
    }
    void setPassword(String p){
        this.password=p;
    }
    String getPassword(){
        return this.password;
    }
    
    static void display_student(){
        JDialog jd=new JDialog();
        jd.setTitle("Student Information Display");
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jd.setResizable(false);

        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti2.jpeg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };                              
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);

        jd.setVisible(true);
        jd.setSize(800,700);
        jd.setLayout(null);

        JPanel verifyPanel=new JPanel();
        verifyPanel.setLayout(new GridLayout(3,2));
        verifyPanel.setBounds(150,300,500,120);
        JPanel infoPanel=new JPanel();
        infoPanel.setLayout(new GridLayout(0,2));

        JLabel id=new JLabel("Enter your ID:");
        id.setForeground(Color.white);
        id.setBackground(new Color(0,0,0,0));
        id.setFont(new Font("Serif",Font.ITALIC,20));
        id.setPreferredSize(new Dimension(200,30));
        JTextField idField=new JTextField();
        idField.setPreferredSize(new Dimension(200,30));
        idField.setBackground(Color.black);
        idField.setForeground(Color.green);
        idField.setFont(new Font("Serif",Font.ITALIC,20));
        JLabel pass=new JLabel("Enter your password: ");
        pass.setForeground(Color.white);
        pass.setBackground(new Color(0,0,0,0));
        pass.setFont(new Font("Serif",Font.ITALIC,20));
        JPasswordField passwordField=new JPasswordField();
        passwordField.setBackground(Color.black);
        passwordField.setForeground(Color.green);
        passwordField.setFont(new Font("Serif",Font.ITALIC,20));

        JButton idb=new JButton("Next");
        idb.setBackground(new Color(0,0,0));
        idb.setForeground(new Color(255,255,255));
        idb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                        BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        idb.setFocusable(false);

        idb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int c=0;
                if(e.getSource()==idb){
                    for(Student x:Admin.Students){
                        if(idField.getText().equals(x.getid())){
                            if(new String(passwordField.getPassword()).equals(x.getPassword())){
                                verifyPanel.removeAll();
                                jd.remove(verifyPanel);
                                infoPanel.setBounds(250,80,500,500);
                                infoPanel.add(new JLabel("Student ID: "));
                                infoPanel.add(new JLabel(x.getid()));
                                infoPanel.add(new JLabel("Name: "));
                                infoPanel.add(new JLabel(x.getname()));
                                infoPanel.add(new JLabel("Age:"));
                                infoPanel.add(new JLabel((x.getage()+"")));
                                infoPanel.add(new JLabel("Gender:"));
                                infoPanel.add(new JLabel(x.getsex()));
                                infoPanel.add(new JLabel("Program:"));
                                infoPanel.add(new JLabel(x.getprog()));
                                infoPanel.add(new JLabel("Branch:"));
                                infoPanel.add(new JLabel(x.getbranch()));
                                infoPanel.add(new JLabel("Current semester:"));
                                infoPanel.add(new JLabel(x.getsem()+""));
                                for(int i=0;i<x.sem_gpas.length;i++){
                                    infoPanel.add(new JLabel("Sem-"+(i+1)+" gpa:"));
                                    infoPanel.add(new JLabel(x.sem_gpas[i]+""));
                                }
                                for(Component component:infoPanel.getComponents()){
                                    component.setFont(new Font("Serif",Font.ITALIC,25));
                                    component.setForeground(Color.white);
                                    component.setBackground(new Color(0,0,0,0));
                                }
                                JButton updateButton=new JButton("Update the data");
                                updateButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        update_data(x);
                                    }
                                });
                                updateButton.setBackground(new Color(0,0,0));
                                updateButton.setForeground(new Color(255,255,255));
                                updateButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                                BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                                updateButton.setFocusable(false);
                                updateButton.setBounds(0,0,200,50);
                                jd.add(updateButton);
                                JButton medButton=new JButton("Medical Query");
                                medButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        Hostel_Affairs.makeMedicalQuerry(x);
                                    }
                                });
                                medButton.setBackground(new Color(0,0,0));
                                medButton.setForeground(new Color(255,255,255));
                                medButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                                BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                                medButton.setFocusable(false);
                                medButton.setBounds(0,50,200,50);
                                jd.add(medButton);
                                JButton passButton=new JButton("Change Password");
                                passButton.setBackground(new Color(0,0,0));
                                passButton.setForeground(new Color(255,255,255));
                                passButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                                BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                                passButton.setFocusable(false);
                                passButton.setBounds(0,100,200,50);
                                passButton.addActionListener(eve->{
                                    JTextField orgpassword = new JTextField();
                                    JTextField newpass1 = new JTextField();
                                    JTextField newpass2 = new JTextField();
                                    JPanel panel = new JPanel(new GridLayout(3, 2));
                                    panel.add(new JLabel("Previous Password:"));
                                    panel.add(orgpassword);
                                    panel.add(new JLabel("New Password:"));
                                    panel.add(newpass1);
                                    panel.add(new JLabel("Enter new password again:"));
                                    panel.add(newpass2);
                                    int result = JOptionPane.showConfirmDialog(null, panel, "Password Change", JOptionPane.OK_CANCEL_OPTION);
                                    if (result == JOptionPane.OK_OPTION) {
                                        if(orgpassword.getText().equals(x.getPassword())){
                                            if(newpass1.getText().equals(newpass2.getText())&&newpass1.getText().length()>=8){
                                                x.setPassword(newpass1.getText());
                                                try(MongoClient mongoClient=MongoClients.create(settings)){
                                                    try {
                                                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                                                        MongoCollection<Document> collection=database.getCollection("Students");
                                                        collection.updateOne(new Document("id",x.getid()),new Document("$set",new Document("password",newpass1.getText())));
                                                    }
                                                    catch(Exception exception){
                                                        System.out.println(exception);
                                                    }
                                                }
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Either the new passwords do not match or the new password does not have enough number of characters", "Issue", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Previous Password is wrong!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });
                                jd.add(passButton);
                                infoPanel.setOpaque(false);
                                jd.add(infoPanel);
                                jd.revalidate();
                                jd.repaint();
                            }
                            else{
                                JOptionPane.showMessageDialog(jd, "Wrong password entered", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                        c++;
                    }
                    if(c==Admin.Students.size()){
                        JOptionPane.showMessageDialog(jd, "Invalid ID", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        verifyPanel.setOpaque(false);
        verifyPanel.add(id);
        verifyPanel.add(idField);
        verifyPanel.add(pass);
        verifyPanel.add(passwordField);
        verifyPanel.add(new JLabel(""));
        verifyPanel.add(idb);
        jd.add(verifyPanel);
    }

    
    public static void create_student(){
        JDialog jd=new JDialog();
        jd.setResizable(false);
        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti3.jpeg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);
        jd.setModal(true);
        jd.setLayout(null);
        jd.setTitle("New student signup portal");
        jd.setPreferredSize(new Dimension(1390,750));
        String[] programs={"B.Tech","M.Tech","M.S"};
        String[] branches={"CS","EE","ME","CH","CE","EP"};
        Integer[] semesters={1,2,3,4,5,6,7,8};
        String[] gender={"Male","Female"};
        JLabel name=new JLabel("Enter your name: ");
        JTextField nameField=new JTextField();  
        JLabel age=new JLabel("Enter your age: ");
        JTextField ageField=new JTextField();
        JLabel sex=new JLabel("Enter your gender: ");
        JComboBox<String> sexField=new JComboBox<>(gender);
        JLabel prog=new JLabel("Enter your program: ");
        JComboBox<String> progField=new JComboBox<>(programs);
        JLabel branch=new JLabel("Enter your branch: ");
        JComboBox<String> branchField=new JComboBox<>(branches);
        JLabel sem=new JLabel("Enter your current semester: ");
        JComboBox<Integer> semField=new JComboBox<>(semesters);
        progField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(progField.getSelectedIndex()==1||progField.getSelectedIndex()==2){
                    semField.removeItem(5);
                    semField.removeItem(6);
                    semField.removeItem(7);
                    semField.removeItem(8);
                }
                if(progField.getSelectedIndex()==0&&semField.getItemCount()==4){
                    semField.addItem(5);
                    semField.addItem(6);
                    semField.addItem(7);
                    semField.addItem(8);
                }
            }
        });      
        JLabel pass=new JLabel("Enter your password: ");
        JPasswordField passField=new JPasswordField();
        JButton nextButton=new JButton("Next");
        JButton createButton=new JButton("Create Student");
        JPanel p1=new JPanel();
        p1.setBounds(370,50,600,250);
        p1.setLayout(new GridLayout(8,2));
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(0,2));
        p1.setOpaque(false);
        p1.add(name);
        p1.add(nameField);
        p1.add(age);
        p1.add(ageField);
        p1.add(sex);
        p1.add(sexField);
        p1.add(prog);
        p1.add(progField);
        p1.add(branch);
        p1.add(branchField);
        p1.add(sem);
        p1.add(semField);
        p1.add(pass);
        p1.add(passField);
        p1.add(new JLabel(""));
        p1.add(nextButton);
        for(Component c:p1.getComponents()){
            if(c instanceof JLabel){
                ((JComponent) c).setOpaque(true);
                c.setBackground(new Color(0,0,0,0));
                c.setForeground(Color.white);
                c.setFont(new Font("Serif",Font.ITALIC,15));
            }
            else if(c instanceof JTextField||c instanceof JComboBox||c instanceof JPasswordField){
                c.setForeground(Color.green);
                c.setBackground(Color.black);
            }
            else{
                c.setBackground(new Color(0,0,0));
                c.setForeground(new Color(255,255,255));
                ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                c.setFocusable(false);
            }
        }
        jd.add(p1);


        ArrayList<JLabel> gpLabels=new ArrayList<>();
        ArrayList<JTextField> gpTextFields=new ArrayList<>();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==nextButton&&passField.getPassword().length>=8){

                    p2.setBounds(370,300,600,35*((Integer)semField.getSelectedItem()+1));
                    p2.setOpaque(false);

                    for(int i=0;i<(Integer)semField.getSelectedItem()-1;i++){
                        gpLabels.add(new JLabel("Enter gpa of semester-"+(i+1)+":"));
                        gpLabels.get(i).setOpaque(true);
                        gpLabels.get(i).setBackground(new Color(0,0,0,0));
                        gpLabels.get(i).setForeground(Color.WHITE);
                        gpLabels.get(i).setFont(new Font("Serif",Font.ITALIC,15));
                        gpTextFields.add(new JTextField());
                        gpTextFields.get(i).setBackground(Color.black);
                        gpTextFields.get(i).setForeground(Color.green);
                        p2.add(gpLabels.get(i));
                        p2.add(gpTextFields.get(i)); 
                    }
                p2.add(new JLabel(""));
                p2.add(createButton);
                nameField.setEnabled(false);
                ageField.setEnabled(false);
                sexField.setEnabled(false);
                semField.setEnabled(false);
                progField.setEnabled(false);
                branchField.setEnabled(false);
                passField.setEnabled(false);
                for(Component c:p2.getComponents()){
                    if(c instanceof JLabel){
                        ((JComponent) c).setOpaque(true);
                        c.setBackground(new Color(0,0,0,0));
                        c.setForeground(Color.white);
                        c.setFont(new Font("Serif",Font.ITALIC,15));
                    }
                    else if(c instanceof JTextField||c instanceof JComboBox||c instanceof JPasswordField){
                        c.setForeground(Color.green);
                        c.setBackground(Color.black);
                    }
                    else{
                        c.setBackground(new Color(0,0,0));
                        c.setForeground(new Color(255,255,255));
                        ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                            BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                        c.setFocusable(false);
                    }
                }
                jd.add(p2);
                jd.revalidate();
                jd.repaint();
            }
        }
        });
        

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==createButton){
                    jd.revalidate();
                    jd.repaint();
                    ArrayList<Double> gpas=new ArrayList<>();
                    for(int i=0;i<gpTextFields.size();i++){
                        gpas.add(Double.parseDouble(gpTextFields.get(i).getText()));
                    }
                    
                try(MongoClient mongoClient = MongoClients.create(settings)){
                    try {
                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                    MongoCollection<Document> collection=database.getCollection("Enroll_Requests");
                    Document document=new Document("name",nameField.getText())
                                                    .append("age",Integer.parseInt(ageField.getText()))
                                                    .append("sex",(String)sexField.getSelectedItem())
                                                    .append("program",(String)progField.getSelectedItem())
                                                    .append("branch",(String)branchField.getSelectedItem())
                                                    .append("cur_sem",(Integer)semField.getSelectedItem())
                                                    .append("password",new String(passField.getPassword()))
                                                    .append("GPAs",gpas);
                    collection.insertOne(document);
                    System.out.println("Document inserted in mongodb successfully");
                    } catch (Exception ex) {
                        // TODO: handle exception
                        System.out.println(ex);
                    }
                }
                    jd.dispose();
                }
            }
        });
        jd.pack();
        jd.setVisible(true);
    }

    static void update_data(Student x){
        JDialog jd=new JDialog();
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti5.jpeg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };                             
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);

        jd.setVisible(true);
        jd.setSize(800,700);
        jd.setLayout(null);
        JPanel updatePanel1=new JPanel();
        updatePanel1.setBounds(100,0,600,400);
        updatePanel1.setLayout(new GridLayout(0,3));
        JPanel updatePanel2=new JPanel();
        updatePanel2.setLayout(new GridLayout(0,3));

        JTextField nameField=new JTextField();
        JTextField ageField=new JTextField();
        JTextField semField=new JTextField();
        ArrayList<JTextField> gpaFields=new ArrayList<>();
        ArrayList<Double> gpas=new ArrayList<>();

        updatePanel1.setBackground(new Color(0,0,0,50));
        updatePanel1.add(new JLabel("Credentials"));
        updatePanel1.add(new JLabel("Current data"));
        updatePanel1.add(new JLabel("Update"));
        updatePanel1.add(new JLabel("Student ID: "));
        updatePanel1.add(new JLabel(x.getid()));
        updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Name: "));
        updatePanel1.add(new JLabel(x.getname()));
        updatePanel1.add(nameField);
        updatePanel1.add(new JLabel("Age:"));
        updatePanel1.add(new JLabel((x.getage()+"")));
        updatePanel1.add(ageField);
        updatePanel1.add(new JLabel("Gender:"));
        updatePanel1.add(new JLabel(x.getsex()));
        updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Program:"));
        updatePanel1.add(new JLabel(x.getprog()));
        updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Branch:"));
        updatePanel1.add(new JLabel(x.getbranch()));
        updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Current semester:"));
        updatePanel1.add(new JLabel(x.getsem()+""));
        updatePanel1.add(semField);
        JButton nextButton=new JButton("Next");
        nextButton.setForeground(Color.white);
        nextButton.setBackground(new Color(0,0,0));
        nextButton.setFocusable(false);
        nextButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        updatePanel1.add(new JLabel(""));
        updatePanel1.add(nextButton);
        updatePanel1.add(new JLabel(""));
        for(Component component:updatePanel1.getComponents()){
            component.setFont(new Font("Serif",Font.TRUETYPE_FONT,20));
            if(component instanceof JTextField){component.setForeground(Color.green);}
            else{component.setForeground(Color.white);}
            component.setBackground(new Color(0,0,0));
        }
        jd.add(updatePanel1);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==nextButton){
                    for(Component component:updatePanel1.getComponents()){
                        if(component instanceof JTextField||component instanceof JButton){component.setEnabled(false);}
                    }
                    int sem=Integer.parseInt(semField.getText());
                    updatePanel2.setBounds(100,400,600,25*sem);
                    updatePanel2.setOpaque(false);
                    for(int i=0;i<Integer.parseInt(semField.getText())-1;i++){
                        JTextField tf=new JTextField();
                        gpaFields.add(tf);
                        updatePanel2.add(new JLabel("Sem-"+(i+1)+" gpa:"));
                        if(i<x.sem_gpas.length){updatePanel2.add(new JLabel(""+x.sem_gpas[i]));}
                        else{updatePanel2.add(new JLabel("null"));}
                        updatePanel2.add(tf);
                    }
                    JButton updateButton=new JButton("Update");
                    updateButton.setBounds(300,400+(25*sem),200,40);
                    updateButton.setForeground(Color.WHITE);
                    updateButton.setBackground(new Color(0,0,0));
                    updateButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                            BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
                    updateButton.setFocusable(false);
                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            for(int i=0;i<Integer.parseInt(semField.getText())-1;i++){
                                gpas.add(Double.valueOf(gpaFields.get(i).getText()));
                            }
                            if(e.getSource()==updateButton){
                                try(MongoClient mongoClient=MongoClients.create(settings)){
                                    try {
                                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                                        MongoCollection<Document> collection=database.getCollection("Update_Requests");
                                        Document document=new Document("id",x.getid())
                                        .append("name",nameField.getText())
                                        .append("age",Integer.parseInt(ageField.getText()))
                                        .append("cur_sem", Integer.parseInt(semField.getText()))
                                        .append("GPAs",gpas);
                                        collection.insertOne(document);
                                    } catch (Exception ex) {
                                        // TODO: handle exception
                                    }
                                }
                                
                                jd.dispose();
                            }
                        }
                    });
                    jd.add(updateButton);
                    for(Component component:updatePanel2.getComponents()){
                        component.setFont(new Font("Serif",Font.ITALIC,15));
                        component.setForeground(Color.white);
                        component.setBackground(new Color(0,0,0));
                    }
                    jd.add(updatePanel2);
                    jd.revalidate();
                    jd.repaint();
                }
            }
        });
        jd.revalidate();
        jd.repaint();
    }
} 