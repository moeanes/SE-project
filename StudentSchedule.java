import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentSchedule {
    private JPanel panel1;
    private JTable table1;

    public StudentSchedule(Student student) {
        createTable(student);
    }

    public JPanel getRootPanel() {
        return panel1;
    }


    private void createTable(Student student) {
        Object[][] data = new Object[17][8];
        int time = 510;
        for(int i = 0; i < 17; i++) {
            int hour = time/60;
            int min = time%60;
            data[i][0] = hour + ":" + min;
            time += 55;
        }
        for(Course c : student.getLessons()) {
            int startDuration = c.getTimeStart();
            int endDuration = c.getTimeEnd();
            int start = 30600;
            int startIndex = startDuration - start;
            int index = startIndex/(55*60);
            int duration = endDuration-startDuration;
            int count = duration/(55*60);
            int column = -1;

            /*
            int hour = duration/(60*60);
            duration = duration%(60*60);
            int min = duration/60;

            int hourStart = startDuration/(60*60);
            startDuration = startDuration%(60*60);
            int minStart = startDuration/60;

            int hourEnd = endDuration/(60*60);
            endDuration = endDuration%(60*60);
            int minEnd = endDuration/60;
            */
            if(c.getDay().equals("Monday")) {column = 1;}
            else if (c.getDay().equals("Tuesday")) {column = 2;}
            else if (c.getDay().equals("Wednesday")) {column = 3;}
            else if (c.getDay().equals("Thursday")) {column = 4;}
            else if (c.getDay().equals("Friday")) {column = 5;}
            else if (c.getDay().equals("Saturday")) {column = 6;}
            else if (c.getDay().equals("Sunday")) {column = 7;}

            for(int i = index; i < index+count; i++) {
                data[i][column] = c.getCourseName() + " - " + c.getLessonClass().getClassName();
            }
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}
        ));
    }

}
