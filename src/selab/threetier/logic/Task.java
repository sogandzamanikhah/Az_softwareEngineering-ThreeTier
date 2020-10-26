package selab.threetier.logic;

import selab.threetier.storage.Storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task extends Entity {
    private String title;
    private Date start;
    private Date end;
    private int valid1 = 0;
    private int valid2 = 1;

    public String getTitle() { return title; }

    public void setTitle(String value) { title = value; }

    public void setStart(Date value) {

        start = value;
    }
    public String getStartDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(start);
    }
    public String getStartTime() {
        return new SimpleDateFormat("HH:mm:ss").format(start);
    }
    public Date getStart(){
        return start;
    }

    public void setEnd(Date value) {
        valid1 = value.compareTo(start);
        end = value;

    }
    public String getEndTime() {
        return new SimpleDateFormat("HH:mm:ss").format(end);
    }
    public Date getEnd(){
        return end;
    }

    public void save() {
        int compare1;
        int compare2;
        int compare3;
        int compare4;

        for (int i = 0; i < Storage.getInstance().getTasks().getsize() ; i++) {

            Date startTask = Storage.getInstance().getTasks().getAll().get(i).start;
            Date endTask = Storage.getInstance().getTasks().getAll().get(i).end;

            compare1 = startTask.compareTo(start);
            compare2 = startTask.compareTo(end);
            compare3 = endTask.compareTo(start);
            compare4 = endTask.compareTo(end);

            if(!((compare1 == 1 && compare2 ==1) || (compare3 == -1 && compare4 == -1))){
                valid2 = 0;
            }

        }

        if(valid1 == 1 && valid2==1){
            valid1 = 0;
            valid2 = 1;
            Storage.getInstance().getTasks().addOrUpdate(this);
        }






    }

    public void remove() {
        Storage.getInstance().getTasks().remove(this);

    }


    public static ArrayList<Task> getAll() {

        return Storage.getInstance().getTasks().getAll();
    }
}
