package com.rkylin.risk.batch.hello;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @company: rkylin
 * @author: tongzhuyu
 * @since: 2015/10/23 version: 1.0
 */
public class WriteTasklet implements Tasklet {
  /** Message */
  private String message;

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
      throws Exception {
    System.out.println(message);
    return RepeatStatus.FINISHED;
  }
}