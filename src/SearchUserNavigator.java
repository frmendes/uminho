import java.util.ArrayList;

/**
 *
 * @author frmendes
 */
public class SearchUserNavigator extends Navigator<User> {

    private FitnessUM app;

    public SearchUserNavigator() {
        super();
        this.app = new FitnessUM();
    }

    public SearchUserNavigator(ArrayList<User> list) {
        super(list);
    }

    public SearchUserNavigator(ArrayList<User> list, FitnessUM app) {
        super(list);
        this.app = app;
    }

    public void print(User u) {
        System.out.println( u.getName() + "\n   " + u.getEmail() );
    }

    public void select(final User u) {
        System.out.println("0. Go Back\n1. View Profile\n 2. Add Friend");
        int option = Scan.menuOption(0, 2);
        new Prompt[] {
            new Prompt() { public void exec() { }},
            new Prompt() { public void exec() { System.out.println(u); } },
            new Prompt() { public void exec() { app.addFriend(u); }}
        }[option].exec();
    }

    public String emptyMessage() {
        return "\nNo results found.\n";
    }
}