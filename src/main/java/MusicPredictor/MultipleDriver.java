/**
 * 
 */
package MusicPredictor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;

/**
* <p>Title: MultipleDriver.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: Zhongwei</p>
* @author Zhongwei
* @date 2016年6月16日
* @version 1.0
*/
public class MultipleDriver extends Configured implements Tool {
	
	private static int NUM_REDUCER = 1;
	private static String MARS_TIANCHI_SONGS = "mars_tianchi_songs.csv";
	private static String MARS_TIANCHI_USER_ACTIONS = "mars_tianchi_user_actions.csv";
	private static String MARS_TIANCHI_AFFAIRS = "mars_tianchi_affairs.csv";
	
	private static String DELIMITER = ",";
	
	
	public static void main(String[] args) throws Exception {  
        Configuration conf=new Configuration();  
//      conf.set("fs.defaultFS", "hdfs://node33:8020");    
//      conf.set("mapreduce.framework.name", "yarn");    
//      conf.set("yarn.resourcemanager.address", "node33:8032");   
        System.out.println("??");
        //ToolRunner.run(conf, new MultipleDriver(), args);  
    } 
	/**
	 * @MethodName     : public int run(String[] arg0) throws Exception
	 * @Description    : 运行Job，两个Mapper:ActionsMapper和SongsMapper，一个Reducer，最终的数据格式是<affair_id, artists_set>。
	 * @param args     : String[] arg0
	 */
	/* (non-Javadoc)
	 * @see org.apache.hadoop.util.Tool#run(java.lang.String[])
	 */
	@SuppressWarnings({ "rawtypes" })
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = getConf();
		conf.set("delimiter", DELIMITER);
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		
		job.setJobName("tianchi");
		job.setJarByClass(MultipleDriver.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(TextArrayWritable.class);//??or Text
		
		job.setReducerClass(AffairReducer.class);
		job.setNumReduceTasks(NUM_REDUCER);
		
		Path inputSongs = new Path(MARS_TIANCHI_SONGS);
		Path inputActions = new Path(MARS_TIANCHI_USER_ACTIONS);
		Path output = new Path(MARS_TIANCHI_AFFAIRS);
		
		MultipleInputs.addInputPath(job, inputSongs, TextInputFormat.class, (Class<? extends Mapper>) SongsMapper.class);
		MultipleInputs.addInputPath(job, inputActions, TextInputFormat.class, (Class<? extends Mapper>) ActionsMapper.class);
		
		FileOutputFormat.setOutputPath(job, output);
		
		int isCompleted = job.waitForCompletion(true) ? 0 : 1;
		
		return isCompleted;
	}
	
	/*
	private void configureArgs(String[] args) {
		
	}
	*/
}
