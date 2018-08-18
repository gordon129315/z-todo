package zz.client;

import com.zzpublic.zwing.View;
import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import zz.client.ui.MainView;


public class Client {

    public static void main(String[] args) {
        View view = new MainView();
        ViewFlow viewFlow = new ViewFlow();
        viewFlow.push(view);
        Window window = new Window(viewFlow);
        window.setVisible(true);
        window.setResizable(true);


    }
}
