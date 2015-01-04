package cli;

import asg.cliche.Command;
import packet.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Commands {
    private Dispatcher dispatcher;

    Commands(Dispatcher d){
        dispatcher = d;
    }

    @Command(name = "register", abbrev = "rg")
    public boolean register(String username, String password){
        Login lg = new Login();

        lg.q_createUser = true;
        lg.q_username = username;
        lg.q_password = password;

        lg = dispatcher.doLogin(lg);

        if(lg.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : lg.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return false;
        }
        return true;
    }

    @Command(name = "login", abbrev = "lg")
    public boolean login(String username, String password){
        Login lg = new Login();

        lg.q_createUser = false;
        lg.q_username = username;
        lg.q_password = password;

        lg = dispatcher.doLogin(lg);

        if(lg.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : lg.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return false;
        }

        System.out.println("Welcome!");
        return true;
    }

    @Command(name="createTaskType", abbrev = "ctt")
    public void createTaskType(String name, String... argsS){
        HashMap<String, Integer> itens = new HashMap<>();
        boolean shouldStr = true;
        String str="";
        int i;

        for(String s : argsS) {
            if (shouldStr) {
                shouldStr = false;
                str = s;
            }
            else if(!shouldStr && ((i = goodInput(s)) > 0) ){
                shouldStr = true;
                itens.put(str,i);
            }
            else {
                System.out.println( str + " not taken into account as need. " + name + " must have, at least, 1 valid need to be created");
                return;
            }
        }

        if(itens.isEmpty()){
            System.out.println("Bad input. Please enter need quantity");
            return;
        }

        CreateTaskType ctt = new CreateTaskType();
        ctt.q_name = name;
        ctt.q_itens = itens;

        ctt = dispatcher.doCreateTaskType(ctt);

        if(ctt.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : ctt.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return;
        }
    }

    @Command(name = "startTask", abbrev = "st")
    public void startTask(String name){
        StartTask st = new StartTask();

        st.q_name = name;
        st = dispatcher.doStartTask(st);

        if(st.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : st.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return;
        }
    }


    @Command(name = "finishTask", abbrev = "ft")
    public void finishTask(int i){
        FinishTask ft = new FinishTask();

        ft.q_taskID = i;
        ft = dispatcher.doFinishTask(ft);

        if(ft.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : ft.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return;
        }
    }

    @Command(name = "listAll", abbrev = "la")
    public void listAll(){
        ListAll la = new ListAll();

        la = dispatcher.doListAll(la);

        if(la.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String s : la.r_errors)
                sb.append(s).append("\n");
            System.err.println(sb.toString());
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(la.r_instances.size()).append(" task types\n");

        int totalInstances = 0;
        for(String t : la.r_instances.keySet())
            totalInstances += la.r_instances.get(t).size();
        sb.append(totalInstances).append(" tasks currently running");

        for(String t : la.r_instances.keySet()) {
            sb.append("\n  ")
                    .append(t)
                    .append("(")
                    .append(la.r_instances.get(t).size())
                    .append("): ");

            Collection<Integer> c = la.r_instances.get(t);
            for (Integer i : c)
                sb.append(i).append(", ");
        }
        sb.delete(sb.length()-2, sb.length()-1).append("\n");
        System.out.print(sb.toString());
    }

    @Command(name = "store", abbrev = "s")
    public void store(String name, int amount){
        Store s = new Store();

        s.q_name = name;
        s.q_quantity = amount;
        s = dispatcher.doStore(s);

        if(s.r_errors.size() > 0) {
            StringBuilder sb = new StringBuilder("\nError(s):\n");
            for (String str : s.r_errors)
                sb.append(str).append("\n");
            System.err.println(sb.toString());
            return;
        }
    }

    @Command(name = "subscribe", abbrev = "sub")
    public void subscribe(int... argsI){
        Subscribe sb = new Subscribe();
        HashSet<Integer> ids = new HashSet<Integer>();

        for(int i : argsI)
            ids.add(i);

        sb.q_ids = new HashSet<>(ids);
        if( !dispatcher.doSubscribe(sb) ){
            System.err.println("Could not send the subscribe packet.");
        }
    }

    public int goodInput(String str){
        int res;

        try{
            res = Integer.parseInt(str);
        } catch(NumberFormatException e){
            System.out.println("\nBad Input. Need quantity must be a number");
            return -1;
        }
            return res;
    }
}
