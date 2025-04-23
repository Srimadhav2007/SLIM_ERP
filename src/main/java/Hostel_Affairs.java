import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.awt.*;
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
import com.mongodb.client.model.Filters;

public class Hostel_Affairs {
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
                                                                .maxWaitTime(1000, java.util.concurrent.TimeUnit.MILLISECONDS))
                                    .build();
    public static void makeMedicalQuerry(Student x){
        JDialog jd=new JDialog();
        jd.setResizable(false);
        jd.setTitle("Student Enroll Requests");
        JPanel imagePanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(new File(System.getProperty("user.dir"),"Images/insti17.jpg").getAbsolutePath()).getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };
        imagePanel.setLayout(null);
        jd.setContentPane(imagePanel); 
        jd.setLayout(null);
        jd.setTitle("Medical Query");
        jd.setPreferredSize(new Dimension(1390,750));
        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JTextArea infTextArea = new JTextArea(
                              "Monday          -    Ayurvedic\n"
                            + "Tuesday          -    ENT and Ophthalmologist\n"
                            + "Wednesday   -     Dental\n"
                            + "Thursday       -    Dermatologist and Cardiologist\n"
                            + "Friday            -    Orthopedician\n"
                            + "Saturday       -    Homeopathy"
                            );
        infTextArea.setEditable(false);
        infTextArea.setBackground(new Color(0,0,0,100));
        infTextArea.setForeground(Color.WHITE);
        infTextArea.setBorder(null);
        infTextArea.setBounds(300,50,700,200);
        infTextArea.setFont(new Font("Sans Serif",Font.ITALIC,23));

        JPanel medInqPanel=new JPanel();
        medInqPanel.setBounds(300,300,700,120);
        medInqPanel.setLayout(new GridLayout(0,2));
        medInqPanel.setBackground(new Color(0,0,0,30));
        medInqPanel.add(new JLabel("The doctor you are visiting:"));
        JComboBox<String> selectBox=new JComboBox<>(new String[] {"Ayurvedic","ENT and Ophthalmologist","Dental","Dermatologist and Cardiologist","Orthopedician","Homeopathy"});
        selectBox.setForeground(Color.green);
        selectBox.setBackground(Color.black);
        medInqPanel.add(selectBox);
        medInqPanel.add(new JLabel("Describe your issue(Optional):"));
        JTextArea issue=new JTextArea();
        issue.setFont(new Font("Sans Serif",Font.PLAIN,15));
        issue.setBackground(Color.black);
        issue.setForeground(Color.green);
        JScrollPane scroller=new JScrollPane(issue);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        medInqPanel.add(scroller);
        medInqPanel.add(new JLabel());
        JButton visitButton=new JButton("Book Visit");
        visitButton.setBackground(Color.black);
        visitButton.setForeground(Color.white);
        visitButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        visitButton.setFocusable(false);
        medInqPanel.add(visitButton);
        visitButton.addActionListener(e->{
            try(MongoClient mongoClient=MongoClients.create(settings)){
            try {
                MongoDatabase database=mongoClient.getDatabase("Student_Info_Portal");
                MongoCollection<Document> collection=database.getCollection("Student_Medical_Data");
                for(Document doc:collection.find()){
                    if(x.getid().equals(doc.getString("id"))){
                        collection.updateOne(Filters.eq("_id", doc.getObjectId("_id")),new Document("$set",new Document("Medical Query-"+(doc.keySet().size()-1),Arrays.asList(new String[] {LocalDate.now().toString(),(String)selectBox.getSelectedItem(),issue.getText()}))));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
            jd.dispose();
        });
        for(Component c:medInqPanel.getComponents()){
                c.setFont(new Font("Sans Serif",Font.ITALIC,23));
                if(c instanceof JLabel){
                    c.setForeground(Color.WHITE);
                }
        }
        jd.add(infTextArea);
        jd.add(medInqPanel);
        jd.setVisible(true);
        jd.pack();
    }
}