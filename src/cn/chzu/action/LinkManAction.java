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
	
	//注入
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//模型驱动方式
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}

	//1、到新增联系人页面的方法
	public String toAddPage() {
		//查询到所有的客户，所有的客户list集合传到页面中显示
		//调用客户service的方法得到所有客户
		//将客户信息放到域对象中去
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	//2、添加联系人信息到数据库
	public String addLinkMan() throws IOException {
		//判断是否需要上传文件
		if (upload!=null) {
			//写上传代码
			//1.在服务器文件夹里面创建文件
			File serverFile = new File("D:\\SSHJar\\ssh_upload"+"\\"+uploadFileName);
			//2.把上传文件复制到服务器文件里面			
			FileUtils.copyFile(upload,serverFile);
		}
		
		//模型驱动方式封装了linkman的数据，但是customer cid(外键)无法封装
		//但是可以放到客户对象中去
		/*
		 * String cid = ServletActionContext.getRequest().getParameter("cid");//获取cid的值
		 * Customer customer = new Customer(); customer.setCid(Integer.parseInt(cid));
		 * linkMan.setCustomer(customer);
		 */
		linkManService.add(linkMan);
		return "addLinkMan";
	}
	
	
	
	//文件上传
	/*
	 * 需要上传文件（流） 
	 * 需要上传文件名称
	 */
	//定义两个变量一个表示上传文件，一个表示上传文件名称
	//上传文件，变量名：表单文件上传项，name属性值
	private File upload;
	//上传文件名称，文件名称命名：文件上传项name属性值FileName
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
	
	//3、联系人列表功能,将查询出的结果放到域对象中去
	public String list() {
		List<LinkMan> list = linkManService.listLinkMan();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	//4、到修改联系人页面的方法
	public String showLinkMan() {
		
		//使用模型驱动得到ID值
		int linkid = linkMan.getLinkid();
		//根据ID查询联系人对象
		LinkMan link = linkManService.findOne(linkid);
		//需要修改的还有所有的客户，需要查询所有的客户放到list集合中去
		List<Customer> listCustomer = customerService.findAll();
		
		//查询到信息后放到域对象中去
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", link);
		request.setAttribute("listCustomer", listCustomer);
		return "showLinkMan";	
		}
	
	
	//5、修改联系人方法
	public String updateLinkMan() {
		linkManService.updateLinkMan(linkMan);
		return "updateLinkMan";
	}
	
	//到联系人信息查询页面
	public String toSelectPage() {
		//查询所有客户，把客户信息传递到下来列表中去
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "toSelectPage";
	}	
	
	//联系人多条件信息查询
	public String moreCondition() {
		//调用方法得到条件后查询的结果
		List<LinkMan> list = linkManService.findCondition(linkMan);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "moreCondition";
	}
	
	
	
	
	
	
	
}
