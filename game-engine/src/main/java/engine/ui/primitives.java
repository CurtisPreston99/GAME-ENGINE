package engine.ui;

import processing.core.PGraphics;

public class primitives {

    // a O(2n) implementation of normilization in a range of data
    static int[] normilzeInRange(int[] data,int max){
        int[] out = new int[data.length];
        int minD=0;
        int maxD=Integer.MIN_VALUE;
        for(int i:data){
            if(i<minD){
                minD=i;
            }
            if(i>maxD){
                maxD=i;
            }
        }
        float m=(maxD-minD);
        if(m==0){
            m=1;
        }
        for(int i=0;i<data.length;i++){
            int newVal= (int) (((data[i] - minD) / m) * max);
            out[i]=newVal;
        }
        return out;
    }

    public static void bargraph(int x,int y,int sizex,int sizey,int[] data,PGraphics b){
        int barW = sizex/data.length;
        // System.out.print(barW);
        int[] normData=normilzeInRange(data,sizey);
        b.fill(UItheme.Singleton().c_light);
        b.rect(x,y,sizex,sizey);
        b.fill(UItheme.Singleton().c_dark);
        for(int i=0;i<normData.length;i++){
            b.rect(x+(i*barW),y+sizey,barW,-normData[i]);
        }
    }


    public static void healthBar(int x,int y,int sizex,int sizey,float progress,PGraphics b){
        healthBar(x, y, sizex, sizey, progress,UItheme.Singleton().c_dark,UItheme.Singleton().c_light, b);
    }


    public static void healthBar(int x,int y,int sizex,int sizey,float progress,int BackColor,int barColor,PGraphics b){
        float prog=(progress/100)*sizex;
        b.fill(BackColor);
        b.rect(x,y,sizex,sizey);
        b.fill(barColor);
        b.rect(x,y,prog,sizey);


    }   
    
}