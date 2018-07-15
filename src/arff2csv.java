import java.io.*;

public class arff2csv {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), "utf-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1]), false), "utf-8"));

        String lineString = bufferedReader.readLine();
        bufferedWriter.write("class,");
        lineString = bufferedReader.readLine();

        while(!lineString.toLowerCase().contains("@data")){
            if(lineString.length()==0){
                lineString = bufferedReader.readLine();
                continue;
            }
            if(lineString.contains("class")){
                lineString = bufferedReader.readLine();
                continue;
            }
            lineString=lineString.split("@")[1].split(" ")[0]+lineString.split("@")[1].split(" ")[1];
            System.out.println(lineString);
            bufferedWriter.write(lineString+",");
            lineString = bufferedReader.readLine();

        }
        bufferedWriter.write("\n");
        lineString = bufferedReader.readLine();
        while(lineString!=null){
            if(lineString.length()==0){
                lineString = bufferedReader.readLine();
                continue;
            }
            String[] linedatas=lineString.split(",");
            int len=0;
            len=linedatas.length;
            bufferedWriter.write(linedatas[len-1]+",");
            for(int i=0;i<len-1;i++){
                bufferedWriter.write(linedatas[i]+",");
            }
            bufferedWriter.write("\n");
            lineString = bufferedReader.readLine();
        }


        bufferedReader.close();
        bufferedWriter.close();
    }
}
