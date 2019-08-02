package cn.chzu.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.chzu.entity.Customer;
import cn.chzu.entity.LinkMan;
import cn.chzu.service.CustomerService;
import cn.chzu.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	
	//ע��
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//ģ��������ʽ
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}

	//1����������ϵ��ҳ��ķ���
	public String toAddPage() {
		//��ѯ�����еĿͻ������еĿͻ�list���ϴ���ҳ������ʾ
		//���ÿͻ�service�ķ����õ����пͻ�
		//���ͻ���Ϣ�ŵ��������ȥ
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	//2�������ϵ����Ϣ�����ݿ�
	public String addLinkMan() throws IOException {
		//�ж��Ƿ���Ҫ�ϴ��ļ�
		if (upload!=null) {
			//д�ϴ�����
			//1.�ڷ������ļ������洴���ļ�
			File serverFile = new File("D:\\SSHJar\\ssh_upload"+"\\"+uploadFileName);
			//2.���ϴ��ļ����Ƶ��������ļ�����			
			FileUtils.copyFile(upload,serverFile);
		}
		
		//ģ��������ʽ��װ��linkman�����ݣ�����customer cid(���)�޷���װ
		//���ǿ��Էŵ��ͻ�������ȥ
		/*
		 * String cid = ServletActionContext.getRequest().getParameter("cid");//��ȡcid��ֵ
		 * Customer customer = new Customer(); customer.setCid(Integer.parseInt(cid));
		 * linkMan.setCustomer(customer);
		 */
		linkManService.add(linkMan);
		return "addLinkMan";
	}
	
	
	
	//�ļ��ϴ�
	/*
	 * ��Ҫ�ϴ��ļ������� 
	 * ��Ҫ�ϴ��ļ�����
	 */
	//������������һ����ʾ�ϴ��ļ���һ����ʾ�ϴ��ļ�����
	//�ϴ��ļ��������������ļ��ϴ��name����ֵ
	private File upload;
	//�ϴ��ļ����ƣ��ļ������������ļ��ϴ���name����ֵFileName
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	//3����ϵ���б���,����ѯ���Ľ���ŵ��������ȥ
	public String list() {
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//4�����޸���ϵ��ҳ��ķ���
	public String showLinkMan() {
		
		//ʹ��ģ�������õ�IDֵ
		int linkid = linkMan.getLinkid();
		//����ID��ѯ��ϵ�˶���
		LinkMan link = linkManService.findOne(linkid);
		//��Ҫ�޸ĵĻ������еĿͻ�����Ҫ��ѯ���еĿͻ��ŵ�list������ȥ
		List<Customer> listCustomer = customerService.findAll();
		
		//��ѯ����Ϣ��ŵ��������ȥ
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);
		return "showLinkMan";	
		}
	
	
	//5���޸���ϵ�˷���
	public String updateLinkMan() {
		linkManService.updateLinkMan(linkMan);
		return "updateLinkMan";
	}
	
	//����ϵ����Ϣ��ѯҳ��
	public String toSelectPage() {
		//��ѯ���пͻ����ѿͻ���Ϣ���ݵ������б���ȥ
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}	
	
	//��ϵ�˶�������Ϣ��ѯ
	public String moreCondition() {
		//���÷����õ��������ѯ�Ľ��
		List<LinkMan> list = linkManService.findCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	
	
	
	
	
	
}
