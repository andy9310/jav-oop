import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays; // Used to print the arrays

import com.google.gson.*;

class test{
    public test(String[] args){
        Mafia sol = new Mafia();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(args[0])){
            JsonArray all = gson.fromJson(reader, JsonArray.class);
            for(JsonElement caseInList : all){
                JsonArray a = caseInList.getAsJsonArray();
                int q_cnt = 0, wa = 0,ac = 0;
                for (JsonElement o : a) {
                    q_cnt++;
                    JsonObject person = o.getAsJsonObject();
                    JsonArray arg_lvl = person.getAsJsonArray("level");
                    JsonArray arg_rng = person.getAsJsonArray("range");
                    JsonArray arg_ans = person.getAsJsonArray("answer");
                    int LVL[] = new int[arg_lvl.size()];
                    int RNG[] = new int[arg_lvl.size()];
                    int Answer[] = new int[arg_ans.size()];
                    int Answer_W[] = new int[arg_ans.size()];
                    for(int i=0;i<arg_ans.size();i++){
                        Answer[i]=(arg_ans.get(i).getAsInt());
                        if(i<arg_lvl.size()){
                            LVL[i]=(arg_lvl.get(i).getAsInt());
                            RNG[i]=(arg_rng.get(i).getAsInt());
                        }
                    }
                    Answer_W = sol.result(LVL,RNG);
                    for(int i=0;i<arg_ans.size();i++){
                        if(Answer_W[i]==Answer[i]){
                            if(i==arg_ans.size()-1){
                                System.out.println(q_cnt+": AC");
                            }
                        }else {
                            wa++;
                            System.out.println(i);
                            System.out.println(Answer_W[i]);
                            System.out.println(Answer[i]);
                          
                            System.out.println(q_cnt+": WA");
                            break;
                        }
                    }

                }
                System.out.println("Score: "+(q_cnt-wa)+"/"+q_cnt);

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class member{
    int Level;
    int Range;
    int Index;
    member(int _level,int _range, int i){
        Level=_level;
        Range=_range;
        Index=i;
    }
}

class Mafia {
    public int[] result(int[] levels, int[] ranges) {
          // Given the traits of each member and output 
          // the leftmost and rightmost index of member
          // can be attacked by each member.
        

        int amount = levels.length;
        int last_bigger_index[] = new int[amount];
        int hit_table[] = new int[amount];
        int answer[] = new int[2*amount];
        int index = 0;
        for(int i = 0;i < amount;i++){
            int min_index = Math.max( last_bigger_index[i] ,Math.max(0, i-ranges[i]));
            // int min_index = Math.max(0, i-ranges[i]);
            int max_index = Math.min(amount-1,i+ranges[i]);
            if(hit_table[i] == 0){
                for(int j = i-1; j >= min_index ;j--){

                    if(levels[i] <= levels[j]){
                        min_index = j+1;
                        break;
                    }
                    
                }
            }
            else{
                for(int j = min_index-1; j >= Math.max(0, i-ranges[i]) ;j--){

                    if(levels[i] <= levels[j]){
                        min_index = j+1;
                        break;
                    }
                    
                }
            }
            for(int j = i+1;j <= max_index ;j++){
                if(levels[i] < levels[j]){
                    max_index = j-1;
                    if(hit_table[j] == 0){
                        last_bigger_index[j] = min_index;
                        hit_table[j] = 1;
                    }
                    break;
                }
                else if(levels[i] == levels[j]){
                    max_index = j-1;
                    if(hit_table[j] == 0){
                        last_bigger_index[j] = i+1;
                        hit_table[j] = 1;
                    }
                    break;
                }
                
            }
            
            answer[index] = min_index;
            answer[index+1] = max_index;
            index = index + 2;
         }
        // ...
        return answer; 
        // complete the code by returning an int[]
        // flatten the results since we only need an 1-dimentional array.
    }

    public static void main(String[] args) {
        Mafia sol = new Mafia();
        System.out.println(Arrays.toString(
            sol.result(new int[] {11, 13, 11, 7, 15},
                         new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
        //      => [a0, b0, a1, b1, a2, b2, a3, b3, a4, b4]
        test new_test = new test(args);
        // new_test.test(args);

    }
}
