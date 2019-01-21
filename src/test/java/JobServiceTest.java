
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.myApp.Dto.JobsRequestDto;
import com.myApp.Dto.StatusResponseDto;
import com.myApp.JobDaoService.JobsService;
import com.myApp.Repo.Jobs;
import com.myApp.Repo.JobsRepository;
import com.myApp.Repo.JobsResultRepository;

public class JobServiceTest {

	@Test
	public void createJobTest()  {
	    
		JobsRepository jobsRepository = mock(JobsRepository.class);
		JobsResultRepository jobsResultRepository = mock(JobsResultRepository.class);
	    //StatusResponseDto statusResponseDto = mock(StatusResponseDto.class);
	    Jobs dbJob = new Jobs();
	    dbJob.setId(1L);
		
	    JobsService jobService = new JobsService(jobsRepository,jobsResultRepository);
	    
	    Set<String> input = new HashSet<>();
	    
	    input.add("http://google.com");
	    when(jobsRepository.save(any(Jobs.class))).thenReturn(dbJob);
	    
	    JobsRequestDto jobsRequestDto = new JobsRequestDto(input);

	    Long result = jobService.createJob(jobsRequestDto);
	    Long expected = 1l;
	    Assert.assertEquals(result, expected);
	
	}
	
	@Test
	public void getJobResponseTest() {
		
	}

}
