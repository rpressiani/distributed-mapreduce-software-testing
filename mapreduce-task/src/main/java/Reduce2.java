import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce2
        extends Reducer<LineWritable2, Text, LineWritable2, Text> {

    public void reduce(LineWritable2 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value : values) context.write(key, value);

    }

}