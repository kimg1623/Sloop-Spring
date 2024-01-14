package kr.co.sloop.member.domain;


import lombok.Data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data/*사용안함*/
public class AttachmentMemberDTO {
  private int attachmentIdx;
  private int postIdx;
  private String attachmentOrgName;
  private String attachmentDirPath;
  private String attachmentName;
  private String attachmentThumbnail;



}
