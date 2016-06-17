/**
 * 
 */
package MusicPredictor;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.util.Tool;

/**
* <p>Title: SongsMapJob.java</p>
* <p>Description: 将song_id与artist_id映射。</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: Zhongwei</p>
* @author Zhongwei
* @date 2016年6月16日
* @version 1.0
*/
public class SongsMapJob extends Configured implements Tool {
	/**
	 * @MethodName     : public int run(String[] arg0) throws Exception
	 * @Description    : 运行Job，使用SongsMapper将mars_tianchi_songs.csv中song_id与artist_id映射。
	 * @param args     : String[] arg0
	 */
	
	/* (non-Javadoc)
	 * @see org.apache.hadoop.util.Tool#run(java.lang.String[])
	 */
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		JobConf conf = new JobConf(ActionsMapper.class);
		
		try {
			Job job = new Job(conf);
			job.setJobName("SongsMapJob");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
