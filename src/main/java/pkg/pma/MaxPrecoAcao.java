package pkg.pma;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MaxPrecoAcao extends Configured implements Tool{
	
	public int run(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.printf("Usage: %s [generic options] <input> <output> \n", getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		Configuration conf = this.getConf();
		Job job = new Job(conf);
		job.setJarByClass(MaxPrecoAcao.class);
		job.setJobName("Max temperature");
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		job.setMapperClass(MaxPrecoAcaoMapper.class);
		job.setReducerClass(MaxPrecoAcaoReducer.class);
		job.setCombinerClass(MaxPrecoAcaoReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new MaxPrecoAcao(), args);
		System.exit(exitCode);

	}

}
