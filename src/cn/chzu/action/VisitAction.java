package cn.chzu.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.chzu.entity.Customer;
import cn.chzu.entity.User;
import cn.chzu.entity.Visit;
import cn.chzu.service.CustomerService;
import cn.chzu.service.UserService;
import cn.chzu.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private VisitService visitService;

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	//ʹ��ģ������
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	
	//ע�����пͻ����û�service
	private UserService userService;
	private CustomerService customerService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//������ҳ��
	public String toAddPage() {
		//��ѯ�����û�
		List<User> listUser = userService.findAll();
		//��ѯ���еĿͻ�
		List<Customer> listCustomer = customerService.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listCustomer", listCustomer);
		return "toAddPage";
	}
	
	//��ӿͻ��ݷù�ϵ
	public String addVisit() {
		visitService.addVisit(visit);
		return "addVisit";
	}
	
	//�ݷ��б�ķ���
	public String list() {
		List<Visit> list = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
}
