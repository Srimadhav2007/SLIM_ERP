import java.awt.event.ActionListener;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;



public class Main {

    static JFrame window;
    static JButton newstudentbButton;
    static JButton studentLoginButton;
    static JPanel panel;
    static JTabbedPane tabbedPane;

    public static void main(String[] args) {
        window=new JFrame("Student Portal");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        window.setSize(screenSize.width-68,screenSize.height-73);
        window.setLayout(null);
        panel=new JPanel(){
            private Image backgroundimage=new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"insti1.jpeg").getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };
        panel.setLayout(null);
        window.setContentPane(panel);
        tabbedPane=new JTabbedPane();
        

        

        Student.counterset();
        Admin.createStudentObjects();
        
        newstudentbButton=new JButton("New Student Enroll");
        newstudentbButton.setBounds(750,500,200,40);
        newstudentbButton.setBackground(new Color(0,0,0));
        newstudentbButton.setForeground(new Color(255,255,255));
        newstudentbButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        newstudentbButton.setFocusable(false);
        newstudentbButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Student.create_student();
            }
        });

        studentLoginButton=new JButton("Student Login");
        studentLoginButton.setBounds(750,550,200,40);
        studentLoginButton.setBackground(new Color(0,0,0));
        studentLoginButton.setForeground(new Color(255,255,255));
        studentLoginButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        studentLoginButton.setFocusable(false);
        studentLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Student.display_student();
            }
        });

        JButton adminloginButton=new JButton("Admin Login");
        adminloginButton.setBounds(750,450,200,40);
        adminloginButton.setBackground(new Color(0,0,0));
        adminloginButton.setForeground(new Color(255,255,255));
        adminloginButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                                    BorderFactory.createLineBorder(new Color(0xf4f5f0),3,false)));
        adminloginButton.setFocusable(false);
        adminloginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Admin.adminLogin();
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabAreaInsets = new Insets(0, 0, 0, 0);
                contentBorderInsets = new Insets(0, 0, 0, 0);
            }
            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                if (isSelected) {
                    g.setColor(new Color(0, 0, 0)); // Selected tab color
                } else {
                    g.setColor(new Color(0x808080)); // Default tab color
                }   
                g.fillRect(x, y, w, h);
            }
        });
        tabbedPane.setBounds(0,0,window.getWidth(),window.getHeight());
        tabbedPane.setFocusable(false);
        UIManager.put("TabbedPane.background", new Color(0, 0, 0, 0));
        UIManager.put("TabbedPane.contentAreaColor", new Color(0, 0, 0, 0));
        tabbedPane.setForeground(Color.white);

        JPanel erpPanel=new JPanel();
        erpPanel.setLayout(null);
        erpPanel.setOpaque(false);
        erpPanel.add(adminloginButton);
        erpPanel.add(newstudentbButton);
        erpPanel.add(studentLoginButton);

        JPanel instiPanel=new JPanel();
        instiPanel.setOpaque(false);
        instiPanel.setLayout(null);
        instiPanel.setBounds(0,0,window.getWidth(),window.getHeight());

        JPanel panelHome = new JPanel();
        panelHome.setLayout(null);
        panelHome.setPreferredSize(new Dimension(instiPanel.getWidth(),instiPanel.getHeight()-20));
        panelHome.setOpaque(false);        
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(0,55,instiPanel.getWidth(),instiPanel.getHeight()-85);       
        ImageIcon rhi1 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi1.png");
        JLabel hi1 = new JLabel(rhi1);
        hi1.setBounds(0,0,instiPanel.getWidth()-660,300);
        ImageIcon rhi2 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi2.png");
        JLabel hi2 = new JLabel(rhi2);
        hi2.setBounds(0,0,instiPanel.getWidth()-660,300);
        ImageIcon rhi3 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi3.png");
        JLabel hi3 = new JLabel(rhi3);
        hi3.setBounds(0,0,instiPanel.getWidth()-660,300);
        ImageIcon rhi4 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi4.png");
        JLabel hi4 = new JLabel(rhi4);
        hi4.setBounds(0,0,instiPanel.getWidth()-660,300);
        ImageIcon rhi5 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi5.png");
        JLabel hi5 = new JLabel(rhi5);
        hi5.setBounds(0,0,instiPanel.getWidth()-660,500);
        ImageIcon rhi6 =new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi6.png");
        JLabel hi6 = new JLabel(rhi6);
        hi6.setBounds(0,0,instiPanel.getWidth()-660,500);
        cardPanel.add(hi1, "hi1");
        cardPanel.add(hi2, "hi2");
        cardPanel.add(hi3, "hi3");
        cardPanel.add(hi4, "hi4");
        cardPanel.add(hi5, "hi5");
        cardPanel.add(hi6, "hi6");
        // Navigation Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(65,505,1880,100);
        JButton nextButton = new JButton(">");
        nextButton.setBounds(1620,0,100,30);
        nextButton.setBackground(new Color(0,0,0,0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBorder(null);
        nextButton.setFocusable(false);
        JButton previousButton = new JButton("<");
        previousButton.setBounds(0,0,100,30);
        previousButton.setBackground(new Color(0,0,0,0));
        previousButton.setForeground(Color.WHITE);
        previousButton.setBorder(null);
        previousButton.setFocusable(false);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardPanel); // Show next card
                instiPanel.revalidate();
                instiPanel.repaint();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cardPanel); // Show previous card
                instiPanel.revalidate();
                instiPanel.repaint();
            }
        });
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        JLabel hi7 = new JLabel(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"hi7.png"));
        hi7.setBounds(30,0,535,82);
        panelHome.add(hi7);
        panelHome.add(buttonPanel, BorderLayout.SOUTH);
        panelHome.add(cardPanel, BorderLayout.CENTER);
        

        JScrollPane scrollPane = new JScrollPane(panelHome,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,30,instiPanel.getWidth(),instiPanel.getHeight());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        JPanel homePanel=new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        JLabel homeLabel=new JLabel(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"insti9.png"));
        homePanel.add(homeLabel);
        homeLabel = new JLabel(
        "<html>The IIT system has 23 Institutions of Technology. The IITs are well known both nationally and internationally for excellence in education and research<br>"
        + "in engineering and science. The research and academic programs are handled by outstanding faculty members and the best students in the country take admission to the IITs<br>"
        + "The prospective students have to appear in the Joint Entrance Examination (JEE) conducted on an all India basis, which around 800,000 students take based<br>"
        + " on which around 200,000 are eligible for the JEE-Advanced examination. About 16,000 students are offered admissions in the B. Tech program in the IITs.<br>"
        + "Over the years, IITs have created world-class educational platforms dynamically sustained through internationally recognized research based on excellent infrastructural facilities.<br>"
        + "The faculty and alumni of IITs have made a huge impact in all sectors of society, both in India and abroad. The institutes are globally recognized as centers of academic excellence<br>"
        + " and are reputed for the outstanding caliber of the students graduating from these Institutions.</html>"
        );
        homeLabel.setFont(new Font("Arial",Font.PLAIN,20));
        homePanel.add(homeLabel);
        homeLabel=new JLabel(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"insti10.png"));
        homeLabel.setOpaque(false);
        homeLabel.setBackground(new Color(0,0,0,0));
        homePanel.add(homeLabel);
        homeLabel=new JLabel(
        "<html>IIT Tirupati operated from the temporary campus on Tirupati – Renigunta Road initially, but all campus activities moved to Yerpedu campus as of 01 August, 2022.<br>"
        + "The Stage 1A Transit Campus (over 36 acres) is integrated into the Permanent Campus spanning over 548.3 acres, provided by the Government of Andhra Pradesh in Merlapaka<br>"
        + "Village on Yerpedu-Venkatagiri Highway. This campus was inaugurated by Honourable Minister of Human Resource Development Shri Ramesh Pokhriyal ‘Nishank‘ on 13th August 2019.<br>"
        + "The Master Plan for the 12,000-student campus has been completed. It shall include four zones, namely, Academic Zone, Hostel Zone, Housing Zone, and Recreational Zone,<br>"
        + "along with a transit campus (that is integrated with the permanent campus). It is planned to build the permanent campus in phases. A complete campus to cater to<br>"
        + "completed by 2021, buildings and 2,500 students, 250 faculty members, and 275 staff members is planned to be built by 2024. The construction is to be taken up in two stages. In Stage 1, to be <br>"
        + "facilities to cater to 1,250 students and 120 faculty members are to be completed, and all operations are to be moved to the permanent campus. Subsequently, the campus shall<br>"
        + "be developed in various phases growing over 25-30 years to cater to a 12,000-student campus. Currently, the campus has air-conditioned classrooms of various capacities<br>"
        + "such as 40, 60, 90, and 120-seater classrooms to conduct classes. Also, high-end Laboratories of Civil, Mechanical, and Electrical engineering are operational at the transit campus.<br>"
        + "In addition, computer Labs, the health center, and the central library are available to facilitate students. The transit campus also hosts various indoor and outdoor sports<br>"
        + "facilities such as Badminton, Table Tennis, Gym, and Volleyball, Basketball, and Football. Supporting facilities such as common dining, laundry, and stationery shops are also available.<br>"
        + "For the upcoming academic year, July 2020, new hostel building construction is going on, which will complete by the end of May 2020.<br>"
        + "It will accommodate around 180 students, including common facilities such as common rooms, study rooms, etc.<br></html>"
        );
        homeLabel.setFont(new Font("Arial",Font.PLAIN,20));
        homePanel.add(homeLabel);

        JPanel mapPanel=new JPanel(){
            private Image backgroundimage=new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"masterplan.png").getImage();
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundimage,0,0,getWidth(),getHeight(),this);
            }
        };
        mapPanel.setLayout(null);
        mapPanel.setPreferredSize(new Dimension(instiPanel.getWidth(),instiPanel.getHeight()));
        JTextArea descArea = new JTextArea(
                            """
        In pursuance of the recommendations of the Site Selection Committee (SSC) and with the approval of the competent authority,
         it was decided to set up IIT Tirupati’s permanent campus at a site in Merlapaka Village,
          Yerpedu (Mandal), Tirupati District, Andhra Pradesh in accordance with Section 93 of the Andhra Pradesh Reorganisation Act, 2014.
           The Government of Andhra Pradesh has proposed to provide land to an extent of about 530 acres for the development of the permanent campus.
            This land is located on the Yerpedu–Venkatagiri road, 22 km from Tirupati town and 14 km from the airport. It has a scenic mountain backdrop.
             The permanent campus was operational by 2019.           
                                    """
                            );
        descArea.setEditable(false);
        descArea.setBackground(new Color(0,0,0,100));
        descArea.setForeground(Color.WHITE);
        descArea.setBorder(null);
        descArea.setBounds(0,800,1400,150);
        descArea.setFont(new Font("Sans Serif",Font.ITALIC,18));
        JLabel mapHeadding = new JLabel("OUR MAP:");
        mapHeadding.setFont(new Font("Impact",Font.ITALIC,60));
        mapHeadding.setBounds(0,0,600,100);
        mapPanel.add(mapHeadding);
        mapPanel.add(descArea);            
            
        JPanel panelLatestNews = new JPanel();
        panelLatestNews.setLayout(null);
        panelLatestNews.setOpaque(false);
        panelLatestNews.setPreferredSize(new Dimension(instiPanel.getWidth(),4110));
        ImageIcon rli1 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li1.jpeg").getImage().getScaledInstance(instiPanel.getWidth(),500, Image.SCALE_SMOOTH));
        JLabel li1 = new JLabel(rli1);
        li1.setBounds(0,0,instiPanel.getWidth(),500);
        JLabel lt1 = new JLabel("News");
        lt1.setFont(new Font("Impact",Font.ITALIC,100));
        lt1.setBounds(250,100,500,100);
        ImageIcon rli2 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li2.png").getImage().getScaledInstance(instiPanel.getWidth()-100,300, Image.SCALE_SMOOTH));
        JLabel li2 = new JLabel(rli2);
        li2.setBounds(50,250,instiPanel.getWidth()-100,500);
        ImageIcon rli3 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li3.png").getImage().getScaledInstance(instiPanel.getWidth()-100,300, Image.SCALE_SMOOTH));
        JLabel li3 = new JLabel(rli3);
        li3.setBounds(50,540,instiPanel.getWidth()-100,500);
        ImageIcon rli4 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li4.png").getImage().getScaledInstance(instiPanel.getWidth()-100,300, Image.SCALE_SMOOTH));
        JLabel li4 = new JLabel(rli4);
        li4.setBounds(50,840,instiPanel.getWidth()-100,500);
        ImageIcon rli5 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li5.png").getImage().getScaledInstance(instiPanel.getWidth()-100,300, Image.SCALE_SMOOTH));
        JLabel li5 = new JLabel(rli5);
        li5.setBounds(50,1130,instiPanel.getWidth()-100,500);
        ImageIcon rli6 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li6.png").getImage().getScaledInstance(instiPanel.getWidth()-100,650, Image.SCALE_SMOOTH));
        JLabel li6 = new JLabel(rli6);
        li6.setBounds(50,1430,instiPanel.getWidth()-100,650);
        ImageIcon rli7 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li7.png").getImage().getScaledInstance(instiPanel.getWidth()-100,650, Image.SCALE_SMOOTH));
        JLabel li7 = new JLabel(rli7);
        li7.setBounds(50,2080,instiPanel.getWidth()-100,650);
        ImageIcon rli8 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li8.png").getImage().getScaledInstance(instiPanel.getWidth()-100,650, Image.SCALE_SMOOTH));
        JLabel li8 = new JLabel(rli8);
        li8.setBounds(50,2730,instiPanel.getWidth()-100,650);
        ImageIcon rli9 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"li9.png").getImage().getScaledInstance(instiPanel.getWidth()-100,650, Image.SCALE_SMOOTH));
        JLabel li9 = new JLabel(rli9);
        li9.setBounds(50,3380,instiPanel.getWidth()-100,650);
        ImageIcon rli10 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"endpic.png").getImage().getScaledInstance(instiPanel.getWidth(),110, Image.SCALE_SMOOTH));
        JLabel li10 = new JLabel(rli10);
        li10.setBounds(0,3980,instiPanel.getWidth(),110);
        panelLatestNews.add(li10);
        panelLatestNews.add(li9);
        panelLatestNews.add(li8);
        panelLatestNews.add(li7);
        panelLatestNews.add(li6);
        panelLatestNews.add(li5);
        panelLatestNews.add(li4);
        panelLatestNews.add(li3);
        panelLatestNews.add(li2);
        panelLatestNews.add(lt1);
        panelLatestNews.add(li1);

        JPanel panelCult = new JPanel();
        panelCult.setPreferredSize(new Dimension(instiPanel.getWidth(),instiPanel.getHeight()));
        JPanel panelCultural = new JPanel();
        panelCultural.setPreferredSize(new Dimension(1500,700));
        panelCultural.setLayout(new GridLayout(0,3));
        ImageIcon rci1 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals1.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci1 = new JLabel(rci1);
        ImageIcon rci2 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals2.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci2 = new JLabel(rci2);
        ImageIcon rci3 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals3.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci3 = new JLabel(rci3);
        ImageIcon rci4 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals4.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci4 = new JLabel(rci4);
        ImageIcon rci5 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals5.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci5 = new JLabel(rci5);
        ImageIcon rci6 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"culturals6.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel ci6 = new JLabel(rci6);
        JLabel cultHeadding = new JLabel("OUR CULTURALS:");
        cultHeadding.setFont(new Font("Impact",Font.ITALIC,60));
        cultHeadding.setBounds(0,0,650,100);
        cultHeadding.setBackground(new Color(0,0,0,75));
        cultHeadding.setForeground(Color.BLACK);
        panelCult.add(cultHeadding);
        panelCult.add(panelCultural);
        panelCultural.add(ci6);
        panelCultural.add(ci1);
        panelCultural.add(ci2);
        panelCultural.add(ci3);
        panelCultural.add(ci4);
        panelCultural.add(ci5);

        JPanel panelSports = new JPanel();
        panelSports.setPreferredSize(new Dimension(instiPanel.getWidth(),instiPanel.getHeight()));
        JPanel panelSpt = new JPanel();
        panelSpt.setPreferredSize(new Dimension(1500,700));
        panelSpt.setLayout(new GridLayout(0,3));
        ImageIcon rsi1 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports1.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si1 = new JLabel(rsi1);
        ImageIcon rsi2 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports2.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si2 = new JLabel(rsi2);
        ImageIcon rsi3 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports3.jpg").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si3 = new JLabel(rsi3);
        ImageIcon rsi4 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports4.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si4 = new JLabel(rsi4);
        ImageIcon rsi5 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports5.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si5 = new JLabel(rsi5);
        ImageIcon rsi6 = new ImageIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"Images"+File.separator+"sports6.png").getImage().getScaledInstance(400,300, Image.SCALE_SMOOTH));
        JLabel si6 = new JLabel(rsi6);
        JLabel sptHeadding = new JLabel("OUR SPORTS:");
        sptHeadding.setFont(new Font("Impact",Font.ITALIC,60));
        sptHeadding.setBounds(0,0,700,100);
        sptHeadding.setBackground(new Color(0,0,0,75));
        sptHeadding.setForeground(Color.BLACK);
        panelSports.add(sptHeadding);
        panelSports.add(panelSpt);
        panelSpt.add(si6);
        panelSpt.add(si1);
        panelSpt.add(si2);
        panelSpt.add(si3);
        panelSpt.add(si4);
        panelSpt.add(si5);

        JPanel panelCurriculum = new JPanel();
        panelCurriculum.setPreferredSize(new Dimension(instiPanel.getWidth(),50));
        panelCurriculum.setLayout(null);
        JLabel ct2 = new JLabel("● Curriculum for B.Tech Programs are listed as below ↓");
        ct2.setFont(new Font("Brush Script",Font.ITALIC,30));
        ct2.setBounds(0,45,800,100);
        ct2.setBackground(new Color(0,0,0,75));
        ct2.setForeground(Color.BLACK);
        JLabel ct1 = new JLabel("● Curriculum for M.Tech. Programs are listed as below↓");
        ct1.setFont(new Font("Brush Script",Font.ITALIC,30));
        ct1.setBounds(0,330,900,50);
        ct1.setBackground(new Color(0,0,0,75));
        ct1.setForeground(Color.BLACK);
        String[] urls = {
            "https://files.iittp.ac.in/pdfs/curriculum/2024/EP.pdf",
            "https://files.iittp.ac.in//pdfs/curriculum/2024/CH.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/CEE_CURRICULUM_05.03.2024.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/CSE_Curriculam_05.03.2024.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/EE.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/ME_CURRICULUM_05.03.2024.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/CH%20M.Tech%20Curriculum.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-EWRE-2020-21.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-GTE-2020-21.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-SE-2020-21.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech_DSAI.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-RFMWE-2022-23.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-MVLSI-2021-22.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/M.Tech/M_Tech_SPCML.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2024/M.Tech/M.Tech_2024_SPC.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/M.Tech-DM-Army%20Officers-2020-21.pdf",
            "https://files.iittp.ac.in/pdfs/curriculum/2022/M.Tech/ME%20FT%20M.Tech%20Curriculum.pdf"
        };
        String[] urlNames = {
            "Engineering Physics",
            "Chemical Engineering",
            "Civil Engineering",
            "Computer Science Engineering",
            "Electrical Engineering",
            "Mechanical Engineering",
            "Chemical Engineering",
            "Environmental and Water Resource(Civil Engineering)",
            "Geotechnical Engineering(Civil Engineering)",
            "Structural Engineering(Civil Engineering)",
            "DSAI(Computer Science and Engineering)",
            "RF and MicroWave Engineering(Electrical Engineering)",
            "Microelectronics and VLSI(Electrical Engineering)",
            "SPCML(Electrical Engineering)",
            "SPC(Electrical Engineering)",
            "Design and Manufacturing(Mechanical Engineering)",
            "Thermal Engineering and Engineering Systems"
        };
        JLabel[] cl = new JLabel[17];
        int y = 110;
        for (int i = 0; i < 17; i++) {
            if(i==6){ y += 40;};
            cl[i] = new JLabel("<html><a href=''>→  " + urlNames[i] + "</a></html>");
            cl[i].setBounds(650, y, 400, 30);
            cl[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            final String url = urls[i];
            cl[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            panelCurriculum.add(cl[i]);
            y += 40;
        }
        JLabel currHeadding = new JLabel("§OUR CURRICULUM");
        currHeadding.setFont(new Font("Impact",Font.ITALIC,60));
        currHeadding.setBounds(100,0,800,100);
        currHeadding.setBackground(new Color(0,0,0,75));
        currHeadding.setForeground(Color.BLACK);            
        panelCurriculum.add(ct1);
        panelCurriculum.add(ct2);
        panelCurriculum.add(currHeadding);
        

        JToolBar toolbar=new JToolBar();
        toolbar.setBackground(new Color(0,0,0,0));
        toolbar.setBorder(null);
        JButton home = new JButton("Home");
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(panelHome);
                scrollPane.revalidate();
                scrollPane.repaint();
    
            }
        });
        
        JButton abtUs=new JButton("About IITT ▼");
        JPopupMenu dropabtUs = new JPopupMenu();
        JMenuItem b11 = new JMenuItem("About Us");
        b11.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(homePanel);
            }
        });
        
        JMenuItem b12 = new JMenuItem("Campus Map");
        b12.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(mapPanel);
                
            }
        });
        JMenuItem b13 = new JMenuItem("Latest News");
        b13.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){                
                scrollPane.setViewportView(panelLatestNews);
            }
        });
        dropabtUs.add(b11);
        dropabtUs.add(b12);
        dropabtUs.add(b13);
        abtUs.addActionListener(e->dropabtUs.show(abtUs,0,abtUs.getHeight()));
        JButton campus=new JButton("Campus ▼");
        JPopupMenu dropcampus=new JPopupMenu();
        JMenuItem b21=new JMenuItem("Culturals");
        b21.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(panelCult);
            }
        });
        JMenuItem b22=new JMenuItem("Sports");
        b22.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(panelSports);
            }
        });
        JMenuItem b23=new JMenuItem("Curriculum ▼");
        b23.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPane.setViewportView(panelCurriculum);
            }
        });
        dropcampus.add(b21);
        dropcampus.add(b22);
        dropcampus.add(b23);
        campus.addActionListener(e->dropcampus.show(campus,0,campus.getHeight()));
        
        toolbar.add(home);
        toolbar.add(abtUs);
        toolbar.add(campus);
        for(Component c:toolbar.getComponents()){
            c.setBackground(Color.BLACK);
            c.setForeground(Color.WHITE);
            c.setFocusable(false);
        }
        toolbar.setBounds(0,0,500,30);
        
        instiPanel.add(toolbar);
        instiPanel.add(scrollPane);
        instiPanel.setOpaque(false);
    
        tabbedPane.add("Institute",instiPanel);
        tabbedPane.addTab("ERP Portal", erpPanel);
        window.add(tabbedPane);
        window.setVisible(true);
    }
}