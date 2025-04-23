import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;



public class Admin{
    static ArrayList<Student> Students=new ArrayList<>();
    static String connectionString = "mongodb+srv://phanisrimadhavmula:<mypassword>@cluster0.lvomk.mongodb.net/?appName=Cluster0";

                                static ServerApi serverApi = ServerApi.builder()
                                    .version(ServerApiVersion.V1)
                                    .build();

                                static MongoClientSettings settings = MongoClientSettings.builder()
                                    .applyConnectionString(new ConnectionString(connectionString))
                                    .serverApi(serverApi)
                                    .applyToConnectionPoolSettings(builder -> 
                                                                builder.maxSize(100)
                                                                .minSize(10)
                                                                .maxWaitTime(5000, java.util.concurrent.TimeUnit.MILLISECONDS))
                                    .build();
    public static void createStudentObjects(){
        try(MongoClient mongoClient=MongoClients.create(settings)){
            try {
                MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                MongoCollection<Document> collection=database.getCollection("Students");
                for(Document doc:collection.find()){
                    Student st=new Student(doc.getString("name"), doc.getInteger("age"), doc.getString("sex"), doc.getInteger("cur_sem"), doc.getString("program"), doc.getString("branch"));
                    st.sem_gpas=doc.getList("GPAs",Double.class).stream().mapToDouble(Double::doubleValue).toArray();
                    st.setid(doc.getString("id"));
                    st.setPassword(doc.getString("password"));
                    Students.add(st);
                }
            } catch (Exception ex) {
            }
        }
    }

    private static String id="ad"+(LocalDate.now().getYear()-2000)+"iitt";
    private static String password="password";
    
    static String getid(){
        return id;
    }
    static String getpassword(){
        return password;
    }
    

    public static void adminLogin(){
        JDialog jd=new JDialog();
        jd.setResizable(false);
        jd.setTitle("Student Enroll Requests");

        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti7.jpeg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);
        
        jd.setLayout(null);
        jd.setTitle("Admin Login Portal");
        jd.setPreferredSize(new Dimension(1390,750));
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel idLabel=new JLabel("Enter your ID:");
        idLabel.setOpaque(true);
        idLabel.setBackground(new Color(0,0,0,0));
        idLabel.setForeground(Color.white);
        idLabel.setFont(new Font("Serif",Font.ITALIC,20));
        JTextField idField=new JTextField();
        idField.setForeground(Color.green);
        idField.setBackground(Color.black);
        idField.setFont(new Font("Serif",Font.ITALIC,20));

        JLabel passLabel=new JLabel("Enter your password:");
        passLabel.setOpaque(true);
        passLabel.setBackground(new Color(0,0,0,0));
        passLabel.setForeground(Color.white);
        passLabel.setFont(new Font("Serif",Font.ITALIC,20));
        JPasswordField passField=new JPasswordField();
        passField.setForeground(Color.green);
        passField.setBackground(Color.black);
        passField.setFont(new Font("Serif",Font.ITALIC,25));

        JButton loginButton=new JButton("Login");
        loginButton.setBackground(new Color(0,0,0));
        loginButton.setForeground(new Color(255,255,255));
        loginButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        loginButton.setFocusable(false);

        JButton studentAcceptButton=new JButton("Student Enroll Requests");
        studentAcceptButton.setBackground(new Color(0,0,0));
        studentAcceptButton.setForeground(new Color(255,255,255));
        studentAcceptButton.setBounds(550,350,250,50);
        studentAcceptButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentAcceptButton.setFocusable(false);

        JButton studentUpdateButton=new JButton("Student Update Requests");
        studentUpdateButton.setBackground(new Color(0,0,0));
        studentUpdateButton.setForeground(new Color(255,255,255));
        studentUpdateButton.setBounds(550,400,250,50);
        studentUpdateButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentUpdateButton.setFocusable(false);

        JButton studentaddButton=new JButton("Add Student");
        studentaddButton.setBackground(new Color(0,0,0));
        studentaddButton.setForeground(new Color(255,255,255));
        studentaddButton.setBounds(550,450,250,50);
        studentaddButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentaddButton.setFocusable(false);

        JButton studentupdater=new JButton("Update Student");
        studentupdater.setBackground(new Color(0,0,0));
        studentupdater.setForeground(new Color(255,255,255));
        studentupdater.setBounds(550,500,250,50);
        studentupdater.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentupdater.setFocusable(false);

        JButton studentsviewButton=new JButton("View Students");
        studentsviewButton.setBackground(new Color(0,0,0));
        studentsviewButton.setForeground(new Color(255,255,255));
        studentsviewButton.setBounds(550,550,250,50);
        studentsviewButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentsviewButton.setFocusable(false);

        JPanel panel=new JPanel();
        panel.setOpaque(false);
        panel.setBounds(400,50,500,120);
        panel.setLayout(new GridLayout(3,2));

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel(""));
        panel.add(loginButton);
        jd.add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(idField.getText().equals(Admin.getid())){
                    if(Admin.getpassword().equals(new String(passField.getPassword()))){
                        panel.removeAll();
                        jd.remove(panel);
                        jd.add(studentAcceptButton);
                        jd.add(studentUpdateButton);
                        jd.add(studentaddButton);
                        jd.add(studentupdater);
                        jd.add(studentsviewButton);
                        studentAcceptButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                 
                                try(MongoClient mongoClient = MongoClients.create(settings)){
                                    try {
                                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                                        MongoCollection<Document> collection=database.getCollection("Enroll_Requests");
                                        JDialog jp=new JDialog();
                                        jp.setLayout(new FlowLayout());
                                        for(Document details:collection.find()){
                                            JButton jb=new JButton(details.getString("name")+"-"+details.getString("program")+"-"+details.getString("branch"));
                                            jb.setBackground(new Color(0,0,0));
                                            jb.setForeground(new Color(255,255,255));
                                            jb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                                            BorderFactory.createLineBorder(new Color(0xf4f5f0),2,false)));
                                            jb.setFocusable(false);
                                            jb.setPreferredSize(new Dimension(150,50));
                                            jp.add(jb);
                                            jb.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    jp.remove(jb);
                                                    checkStudentEnroll(details);
                                                }
                                            });
                                        }
                                        jp.pack();
                                        jp.setVisible(true);

                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                            }
                        });
                        studentUpdateButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                try(MongoClient mongoClient = MongoClients.create(settings)){
                                    try {
                                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                                        MongoCollection<Document> collection=database.getCollection("Update_Requests");
                                        JDialog jp=new JDialog();
                                        jp.setLayout(new FlowLayout());
                                        for(Document details:collection.find()){
                                            JButton jb=new JButton(details.getString("name")+"-"+details.getString("id"));
                                            jb.setBackground(new Color(0,0,0));
                                            jb.setForeground(new Color(255,255,255));
                                            jb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                                            BorderFactory.createLineBorder(new Color(0xf4f5f0),2,false)));
                                            jb.setFocusable(false);
                                            jb.setPreferredSize(new Dimension(150,50));
                                            jp.add(jb);
                                            checkStudentUpdate(details,database.getCollection("Students").find(new Document("id",details.getString("id"))).first());
                                            /*jb.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e){
                                                    jp.remove(jb);
                                                    checkStudentUpdate(details,database.getCollection("Students").find(new Document("id",details.getString("id"))).first());
                                                }
                                            });*/
                                        }
                                        jp.pack();
                                        jp.setVisible(true);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                            }
                        });
                        studentaddButton.addActionListener(event->{
                            addStudent();
                        });
                        studentupdater.addActionListener(event->updateStudent());
                        studentsviewButton.addActionListener(event->{
                            view_students();
                        });
                        jd.revalidate();
                        jd.repaint();
                    }
                    else{
                        JOptionPane.showMessageDialog(jd, "Wrong password entered", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(jd, "Invalid ID", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jd.pack();
        jd.setVisible(true);
    }

    

    public static void checkStudentEnroll(Document details){
        final Student st[]=new Student[1];
        JDialog jd=new JDialog();
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jd.setSize(800,700);
        jd.setLayout(null);
        jd.setResizable(false);

        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti4.jpeg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };                              
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);

        JButton yesButton=new JButton("Enroll Student");
        yesButton.setBackground(new Color(0,0,0));
        yesButton.setForeground(new Color(255,255,255));
        yesButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        yesButton.setFocusable(false);
        JButton noButton=new JButton("Refuse the request");
        noButton.setBackground(new Color(0,0,0));
        noButton.setForeground(new Color(255,255,255));
        noButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        noButton.setFocusable(false);

        JPanel checkPanel=new JPanel();
        checkPanel.setLayout(new GridLayout(0,2));
        checkPanel.setBounds(450,50,300,500);

        checkPanel.add(new JLabel("Name:"));
        checkPanel.add(new JLabel(details.getString("name")));
        checkPanel.add(new JLabel("Age:"));
        checkPanel.add(new JLabel(details.getInteger("age")+""));
        checkPanel.add(new JLabel("Gender:"));
        checkPanel.add(new JLabel(details.getString("sex")));
        checkPanel.add(new JLabel("Program:"));
        checkPanel.add(new JLabel(details.getString("program")));
        checkPanel.add(new JLabel("Branch"));
        checkPanel.add(new JLabel(details.getString("branch")));
        checkPanel.add(new JLabel("Current-Semester:"));
        checkPanel.add(new JLabel(details.getInteger("cur_sem")+""));
        double[] doubleArray = details.getList("GPAs",Double.class).stream().mapToDouble(Double::doubleValue).toArray();
        for(int i=0;i<details.getInteger("cur_sem")-1;i++){
            checkPanel.add(new JLabel("Sem-"+(i+1)+" GPA:"));
            checkPanel.add(new JLabel(doubleArray[i]+""));
        }
        for(Component c:checkPanel.getComponents()){
            c.setFont(new Font("Serif",Font.ITALIC,15));
        }
        checkPanel.add(yesButton);
        checkPanel.add(noButton);
        jd.add(checkPanel);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                st[0]=new Student(details.getString("name"),
                                    details.getInteger("age"),
                                    details.getString("sex"),
                                    details.getInteger("cur_sem"),
                                    details.getString("program"),
                                    details.getString("branch"));
                st[0].setPassword(details.getString("password"));
                st[0].sem_gpas=doubleArray;
                Students.add(st[0]);
            
                try(MongoClient mongoClient = MongoClients.create(settings)){
                    try {
                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                        MongoCollection<Document> collection=database.getCollection("Students");
                        Document document=new Document("id",st[0].getid())
                                                        .append("name",st[0].getname())
                                                        .append("age",st[0].getage())
                                                        .append("sex",st[0].getsex())
                                                        .append("program",st[0].getprog())
                                                        .append("branch",st[0].getbranch())
                                                        .append("cur_sem",st[0].getsem())
                                                        .append("password",st[0].getPassword())
                                                        .append("GPAs",Arrays.stream(st[0].sem_gpas).boxed().collect(Collectors.toList()));
                        collection.insertOne(document);
                        MongoCollection<Document> medCollection=database.getCollection("Student_Medical_Data");
                        medCollection.insertOne(new Document("id",st[0].getid()));
                        MongoCollection<Document> orgCollection=database.getCollection("Enroll_Requests");
                        orgCollection.deleteOne(new Document("name",st[0].getname()));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                jd.dispose();
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try(MongoClient mongoClient = MongoClients.create(settings)){
                    try {
                        mongoClient.getDatabase("Student_Info_Portal").getCollection("Enroll_Requests").deleteOne(new Document("name",details.getString("name")));
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                }
                jd.dispose();
            }
        });

        jd.setVisible(true);
    }

    public static void checkStudentUpdate(Document details,Document original){
        JDialog jd=new JDialog();
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jd.setSize(800,700);
        jd.setLayout(null);
        jd.setResizable(false);
        jd.setLocation(650,0);
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

        JPanel updatePanel=new JPanel();
        updatePanel.setBounds(100,0,600,600);
        updatePanel.setLayout(new GridLayout(0,3));
        updatePanel.setOpaque(false);
        updatePanel.add(new JLabel("Credentials"));
        updatePanel.add(new JLabel("Current data"));
        updatePanel.add(new JLabel("Update"));
        updatePanel.add(new JLabel("Student ID: "));
        updatePanel.add(new JLabel(original.getString("id")));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel("Name: "));
        updatePanel.add(new JLabel(original.getString("name")));
        updatePanel.add(new JLabel(details.getString("name")));
        updatePanel.add(new JLabel("Age:"));
        updatePanel.add(new JLabel(original.getInteger("age")+""));
        updatePanel.add(new JLabel(details.getInteger("age")+""));
        updatePanel.add(new JLabel("Gender:"));
        updatePanel.add(new JLabel(original.getString("sex")));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel("Program:"));
        updatePanel.add(new JLabel(original.getString("program")));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel("Branch:"));
        updatePanel.add(new JLabel(original.getString("branch")));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel("Current semester:"));
        updatePanel.add(new JLabel(original.getInteger("cur_sem")+""));
        updatePanel.add(new JLabel(details.getInteger("cur_sem")+""));
        for(int i=0;i<details.getList("GPAs",Double.class).size();i++){
            if(i<original.getList("GPAs",Double.class).size()){
                updatePanel.add(new JLabel("Sem-"+(i+1)+" GPA:"));
                updatePanel.add(new JLabel(original.getList("GPAs",Double.class).get(i)+""));
                updatePanel.add(new JLabel(details.getList("GPAs",Double.class).get(i)+""));
            }
            else{
                updatePanel.add(new JLabel("Sem-"+(i+1)+" GPA:"));
                updatePanel.add(new JLabel("null"));
                updatePanel.add(new JLabel(details.getList("GPAs",Double.class).get(i)+""));
            }
        }
        JButton yesButton=new JButton("Accept Update");
        yesButton.setBackground(new Color(0,0,0));
        yesButton.setForeground(new Color(255,255,255));
        yesButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        yesButton.setFocusable(false);
        yesButton.setBounds(200,600,200,50);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==yesButton){
                    try(MongoClient mongoClient = MongoClients.create(settings)){
                        try {
                                for(Student x:Students){
                                    if(x.getid().equals(original.getString("id"))){
                                    x.setname(details.getString("name"));
                                    System.out.println(x.getname());
                                    x.setage(details.getInteger("age"));
                                    System.out.println(x.getage());
                                    x.setsem(details.getInteger("cur_sem"));
                                    System.out.println(x.getsem());
                                    x.sem_gpas=details.getList("GPAs",Double.class).stream().mapToDouble(Double::doubleValue).toArray();
                                    details.remove("_id");
                                    mongoClient.getDatabase("Student_Info_Portal").getCollection("Students").updateOne(original,new Document("$set",details));
                                    mongoClient.getDatabase("Student_Info_Portal").getCollection("Update_Requests").deleteOne(new Document("name",details.getString("name")));
                                    }
                                }
                        }
                    catch(Exception ex){
                        System.out.println(ex);
                    }
                }
                    jd.dispose();
                }
            }
        });
        JButton noButton=new JButton("Reject Update");
        noButton.setBackground(new Color(0,0,0));
        noButton.setForeground(new Color(255,255,255));
        noButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        noButton.setFocusable(false);
        noButton.setBounds(400,600,200,50);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try(MongoClient mongoClient = MongoClients.create(settings)){
                    try {
                        mongoClient.getDatabase("Student_Info_Portal").getCollection("Update_Requests").deleteOne(new Document("name",details.getString("name")));
                    }catch(Exception ex){

                    }
                    jd.dispose();
                }
            }
        });
        for(Component component:updatePanel.getComponents()){
            component.setFont(new Font("Serif",Font.TRUETYPE_FONT,20));
            if(component instanceof JTextField){component.setForeground(Color.green);}
            else{component.setForeground(Color.white);}
            component.setBackground(new Color(0,0,0));
        }
        jd.add(yesButton);
        jd.add(noButton);
        jd.add(updatePanel);
        
        jd.setVisible(true);
    }
    public static void addStudent(){
        JDialog jd=new JDialog();
        jd.setModalityType(Dialog.ModalityType.MODELESS);
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
                    final Student st=new Student(nameField.getText(),
                                                Integer.parseInt(ageField.getText()),
                                                (String)sexField.getSelectedItem(),
                                                (Integer)semField.getSelectedItem(),
                                                (String)progField.getSelectedItem(),
                                                (String)branchField.getSelectedItem());
                    st.setPassword(new String(passField.getPassword()));
                    st.sem_gpas=gpas.stream().mapToDouble(Double::doubleValue).toArray();
                    Students.add(st);
                    try(MongoClient mongoClient = MongoClients.create(settings)){
                        try {
                            MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                            MongoCollection<Document> collection=database.getCollection("Students");
                            Document document=new Document("id",st.getid())
                                                            .append("name",st.getname())
                                                            .append("age",st.getage())
                                                            .append("sex",st.getsex())
                                                            .append("program",st.getprog())
                                                            .append("branch",st.getbranch())
                                                            .append("cur_sem",st.getsem())
                                                            .append("password",st.getPassword())
                                                            .append("GPAs",Arrays.stream(st.sem_gpas).boxed().collect(Collectors.toList()));
                            collection.insertOne(document);
                            MongoCollection<Document> medCollection=database.getCollection("Student_Medical_Data");
                            medCollection.insertOne(new Document("id",st.getid()));
                        } catch (Exception ex) {
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
    public static void updateStudent(){
        JTextField idField = new JTextField();
        JTextField namField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Enter the ID of the Student:"));
        panel.add(idField);
        panel.add(new JLabel("Enter the name of the Student:"));
        panel.add(namField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Update Wizard", JOptionPane.OK_CANCEL_OPTION);
        if(result==JOptionPane.OK_OPTION){
            for(Student x:Students){
                if(x.getid().equals(idField.getText())&&x.getname().equals(namField.getText())){
                    JDialog jd=new JDialog();
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti20.jpg").getAbsolutePath()).getImage();
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
        updatePanel1.add(new JLabel("Credentials"));updatePanel1.add(new JLabel("Current data"));updatePanel1.add(new JLabel("Update"));
        updatePanel1.add(new JLabel("Student ID: "));updatePanel1.add(new JLabel(x.getid()));updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Name: "));updatePanel1.add(new JLabel(x.getname()));updatePanel1.add(nameField);
        updatePanel1.add(new JLabel("Age:"));updatePanel1.add(new JLabel((x.getage()+"")));updatePanel1.add(ageField);
        updatePanel1.add(new JLabel("Gender:"));updatePanel1.add(new JLabel(x.getsex()));updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Program:"));updatePanel1.add(new JLabel(x.getprog()));updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Branch:"));updatePanel1.add(new JLabel(x.getbranch()));updatePanel1.add(new JLabel(""));
        updatePanel1.add(new JLabel("Current semester:"));updatePanel1.add(new JLabel(x.getsem()+""));updatePanel1.add(semField);
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
                                String prevname=x.getname();
                                x.setname(nameField.getText());
                                x.setage(Integer.parseInt(ageField.getText()));
                                x.setsem(Integer.parseInt(semField.getText()));
                                x.sem_gpas=gpas.stream().mapToDouble(Double::doubleValue).toArray();
                                try(MongoClient mongoClient=MongoClients.create(settings)){
                                    try {
                                        MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                                        MongoCollection<Document> collection=database.getCollection("Students");
                                        collection.updateOne(new Document("id",x.getid()).append("name",prevname),new Document("$set",new Document("name",x.getname()).append("age",x.getage()).append("cur_sem",x.getsem()).append("GPAs",gpas)));
                                    } catch (Exception ex) {
                                        System.out.println(ex);
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
        }
    }
    public static void view_students() {
        JDialog jd = new JDialog();
        jd.setResizable(false);
        jd.setTitle("View Students");
        jd.setPreferredSize(new Dimension(1390, 750));
    
        JPanel imagePanel = new JPanel() {
            private Image backgroundImage = new ImageIcon(new File(System.getProperty("user.dir"), "Images/insti20.jpg").getAbsolutePath()).getImage();
    
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.out.println("Background image not loaded!");
                }
            }
        };
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel);
    
        JPanel studentPanel=new JPanel(new FlowLayout());
        studentPanel.setOpaque(false);
        studentPanel.setBounds(0,0,1390,750);
        for(Student x:Students){
            JButton xb=new JButton(x.getid()+"-"+x.getname());
            xb.setFocusable(false);
            xb.setForeground(Color.white);
            xb.setBackground(Color.black);
            xb.addActionListener(e->{
                JPanel p=new JPanel(new GridLayout(0,2));
                p.add(new JLabel("Name:"));
                p.add(new JLabel(x.getname()));
                p.add(new JLabel("Age:"));
                p.add(new JLabel(x.getage()+""));
                p.add(new JLabel("Gender:"));
                p.add(new JLabel(x.getsex()));
                p.add(new JLabel("Program:"));
                p.add(new JLabel(x.getprog()));
                p.add(new JLabel("Branch:"));
                p.add(new JLabel(x.getbranch()));
                p.add(new JLabel("Sem:"));
                p.add(new JLabel(x.getsem()+""));
                for(int i=0;i<x.sem_gpas.length;i++){
                    p.add(new JLabel("Sem-"+(i+1)+" GPA:"));
                    p.add(new JLabel(x.sem_gpas[i]+""));
                }
                JOptionPane.showMessageDialog(null, p,"Student data", JOptionPane.PLAIN_MESSAGE);
            });
            studentPanel.add(xb);
        }
        
        jd.add(studentPanel);
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jd.pack();
        jd.setVisible(true);
    }
}