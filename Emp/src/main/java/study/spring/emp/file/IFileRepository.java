package study.spring.emp.file;

import java.util.HashMap;
import java.util.List;

import study.spring.emp.file.model.FileVO;

public interface IFileRepository {

	public int getMaxFileId();
	public void uploadFile(FileVO file);
	public FileVO getFile(int fileId);
	public List<FileVO> getAllFileList();
	public List<FileVO> getFileListBydir(String directoryName);
	public void deleteFile(int fileId);
	public void updateFile(FileVO file);
	public void updateDirectory(HashMap<String, Object> map);

}