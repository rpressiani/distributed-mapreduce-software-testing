import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.ArrayList;
import java.util.List;

public class LineCoverage extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {


        JobControl jobControl = new JobControl("Line Coverage Chain");

        Configuration conf1 = getConf();

        GenericOptionsParser optionParser = new GenericOptionsParser(conf1, args);
        String[] remainingArgs = optionParser.getRemainingArgs();

        if ((remainingArgs.length != 3 )) {
            System.err.println("Usage: linecoverage <in> <temp> <out>");
            System.exit(-1);
        }

        List<String> otherArgs = new ArrayList();
        for (int i=0; i < remainingArgs.length; ++i) {
            otherArgs.add(remainingArgs[i]);
        }

        Job job1 = Job.getInstance(conf1);
        job1.setJarByClass(LineCoverage.class);
        job1.setJobName("Line Coverage Task");

        FileInputFormat.setInputPaths(job1, new Path(otherArgs.get(0)));
        FileOutputFormat.setOutputPath(job1, new Path(otherArgs.get(1)));

        job1.setMapperClass(Map.class);
        job1.setReducerClass(Reduce.class);

        job1.setMapOutputKeyClass(LineWritable.class);
        job1.setMapOutputValueClass(Text.class);

        ControlledJob controlledJob1 = new ControlledJob(conf1);
        controlledJob1.setJob(job1);
        jobControl.addJob(controlledJob1);

        Thread jobControlThread = new Thread(jobControl);
        jobControlThread.start();


        Configuration conf2 = getConf();

        Job job2 = Job.getInstance(conf2);
        job2.setJarByClass(LineCoverage.class);
        job2.setJobName("Output Sort");

        FileInputFormat.setInputPaths(job2, new Path(otherArgs.get(1)));
        FileOutputFormat.setOutputPath(job2, new Path(otherArgs.get(2)));

        job2.setMapperClass(Map2.class);
        job2.setReducerClass(Reduce2.class);

        job2.setMapOutputKeyClass(LineWritable2.class);
        job2.setMapOutputValueClass(Text.class);

        ControlledJob controlledJob2 = new ControlledJob(conf2);
        controlledJob2.setJob(job2);

        controlledJob2.addDependingJob(controlledJob1);
        jobControl.addJob(controlledJob2);

        while (!jobControl.allFinished()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
        return (job1.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new LineCoverage(), args);
        System.exit(exitCode);
    }

}