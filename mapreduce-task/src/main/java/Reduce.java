import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class Reduce
        extends Reducer<LineWritable, Text, LineWritable, Text> {

    public void reduce(LineWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        ArrayList<String> tests = new ArrayList();
        int count = 0;
        for (Text value : values) {
            count++;
            tests.add(value.toString());
        }

        StringBuilder result = new StringBuilder();

        for (String test : tests) {
            result.append(test);
            result.append(",");
        }

        result.append(count);

        context.write(key, new Text(result.toString()));
    }

}