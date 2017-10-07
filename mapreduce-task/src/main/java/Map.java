import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map extends Mapper<LongWritable, Text, LineWritable, Text> {
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] parts = line.split(",");

        if (parts.length < 3) {
            return;
        }

        Text test = new Text(parts[0]);
        String className = parts[1];
        Integer lineNumber = Integer.parseInt(parts[2]);

        context.write(new LineWritable(className, lineNumber), test);

    }





}
