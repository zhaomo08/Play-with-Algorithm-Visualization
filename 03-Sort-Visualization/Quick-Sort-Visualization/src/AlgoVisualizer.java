import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private QuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame, QuickSortData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData(-1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        quickSort(0, data.N()-1);

        this.setData(0, data.N()-1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort(int l, int r){

        if( l >= r )
            return;

        //setData(l, r, -1);
        //AlgoVisHelper.pause(DELAY);

        int p = partition(l, r);
        quickSort(l, p-1 );
        quickSort(p+1, r);
    }

    private int partition(int l, int r){

        //setData(l, r, -1);
        //AlgoVisHelper.pause(DELAY);

        int v = data.get(l);

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ )
            if( data.get(i) < v ){
                j ++;
                data.swap(j, i);
            }

        data.swap(l, j);

        //setData(l, r, -1);
        //AlgoVisHelper.pause(DELAY);

        return j;
    }

    private void setData(int l, int r, int pivot){
        data.l = l;
        data.r = r;
        if(pivot != -1)
            data.pivots[pivot] = true;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Selection Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            QuickSortData data = new QuickSortData(N, sceneHeight);
            AlgoVisualizer vis = new AlgoVisualizer(N, frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}