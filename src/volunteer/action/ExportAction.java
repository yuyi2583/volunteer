package volunteer.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import volunteer.po.ActInfo;
import volunteer.service.ExportService;
import volunteer.service.IActivityService;
import volunteer.service.IExportService;

public class ExportAction extends ActionSupport {

	InputStream excelStream;
	private ActInfo info;
	private String fileName;// 下载的文件名
	private String downloadFileName;
	private IExportService exportservice = null;
	public void setExportservice(IExportService exportservice) {
		this.exportservice = exportservice;
	}
	public IExportService getExportservice() {
		return exportservice;
	}
	// 根据活动编号获取活动岗位招募情况
	public String actJobState() throws Exception {
		String Ano = info.getAno();
		//ExportService expSer=new ExportService();
		excelStream = exportservice.getExcelInputStream(Ano);
		return "excel";

	}

	/**
	 * 设置文件名称，downloadFileName要和Struts.xml中的filename对应，
	 * 
	 * @return
	 */
	public String getDownloadFileName() {
		try {
			System.out.println(fileName);
			downloadFileName = java.net.URLEncoder.encode(fileName+".xls", "UTF-8");//new String((fileName + ".xls").getBytes("gb2312"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("fileName:"+fileName);
		return downloadFileName;
	}


	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setInfo(ActInfo info) {
		this.info = info;
	}

	public ActInfo getInfo() {
		return info;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
