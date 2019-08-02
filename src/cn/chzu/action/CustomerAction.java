package cn.chzu.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.chzu.entity.Customer;
import cn.chzu.entity.Dict;
import cn.chzu.entity.PageBean;
import cn.chzu.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		
		return customer;
	}
	

	//1�������ҳ��
	public String toAddPage() {
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	
	//2����ӷ���
	public String add() {
		System.out.println(customer);
		
		customerService.add(customer);
		
		return "add";	
	}
	//�������list
	private List<Customer> list;
	//����list  get����
	public List<Customer> getList() {
		return list;
	}
	//3���û��б���
	public String list() {
		/*
		 * List<Customer> list = customerService.findAll(); //���ص�listֵ��Ҫ����ҳ����ȥ�����Էŵ��������ȥ
		 * System.out.println(list);
		 * ServletActionContext.getRequest().setAttribute("list", list);
		 */
		//�����ݷŵ�ֵջ��ȥ
		list = customerService.findAll();
		return "list";
	}
	
	//4��ɾ���ķ���     �ȸ���ID��ѯ�����÷���ɾ��
	public String delete() {
		//ʹ��ģ��������ȡ���ύcid
		int cid = customer.getCid();
		System.out.println(cid);
		//����ID��ѯ
		Customer c = customerService.findOne(cid);
		
		if (c!=null) {
			customerService.delete(c);
		}
		return "delete";
	}
	
	//5���޸Ĳ���   �ȸ���ID��ѯ����ʾ�ٽ����޸�
	public String showCustomer() {
		//ʹ��ģ��������ȡ���ύcid
		int cid = customer.getCid();
		System.out.println(cid);
		//����ID��ѯ
		Customer c = customerService.findOne(cid);
		//�ŵ��������ȥ
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}
	
	public String update() {
		int cid = customer.getCid();
		System.out.println(cid+"�޸Ļ�ȡcid");
		customerService.update(customer);
		return "update";
	}
	
	
	//ʹ�����Է�װ�ķ�ʽ��ȡ��ǰҳ
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = 1;
	}

	//6����ҳ����
	public String listPage() {
		//����service����ʵ�����ݷ�װ
		
		PageBean pageBean = customerService.listPage(currentPage);
		//�õ���װ�����ݵ�pageBean���󣬽�������������
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listPage";
	}
	
	
	//7��������ѯ�ķ���
	public String listcondition() {
		//�������ͻ����ƣ����ݿͻ����Ʋ�ѯ
		//����������κ����ݣ���ѯ����
		if (customer.getCustName()!=null&&!"".equals(customer.getCustName())) {
			//��Ϊ��
			 list = customerService.findCondition(customer);
			//ServletActionContext.getRequest().setAttribute("list", list);
		}else {
			//û���κ���������
			list = customerService.findAll();
		}
		return "listcondition";
	}
	
	//8�����ͻ���Ϣ��ѯ��ҳ��
	public String toSelectCustomerPage() {
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toSelectCustomerPage";
	}
	
	//9���ۺϲ�ѯ����������ѯ
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		
		return "moreCondition";
	}
	
	//���ݿͻ���Դ����ͳ��
	public String countSource() {
		List list = customerService.findCountSource();
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	//���ݿͻ��������ͳ��
	public String countLevel() {
		List list = customerService.findCountLevel();
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countLevel";
	}

}

/***
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          ���汣��             ����BUG
 */  
