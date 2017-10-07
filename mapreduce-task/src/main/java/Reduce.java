import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce
        extends Reducer<LineWritable, Text, LineWritable, MapWritable> {

    public void reduce(LineWritable key, Iterable<Text> values,
                       Context context
    ) throws IOException, InterruptedException {

        MapWritable result = new MapWritable();
        int count = 0;
        for (Text value : values) {
            count = count + 1;
            result.put(new Text(value), new IntWritable(count));
        }

        context.write(key, result);
    }

}