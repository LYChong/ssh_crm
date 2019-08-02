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
	

	//1、到添加页面
	public String toAddPage() {
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}
	
	//2、添加方法
	public String add() {
		System.out.println(customer);
		
		customerService.add(customer);
		
		return "add";	
	}
	//定义变量list
	private List<Customer> list;
	//生成list  get方法
	public List<Customer> getList() {
		return list;
	}
	//3、用户列表功能
	public String list() {
		/*
		 * List<Customer> list = customerService.findAll(); //返回的list值需要传到页面中去，所以放到域对象中去
		 * System.out.println(list);
		 * ServletActionContext.getRequest().setAttribute("list", list);
		 */
		//将数据放到值栈中去
		list = customerService.findAll();
		return "list";
	}
	
	//4、删除的方法     先根据ID查询，调用方法删除
	public String delete() {
		//使用模型驱动获取表单提交cid
		int cid = customer.getCid();
		System.out.println(cid);
		//根据ID查询
		Customer c = customerService.findOne(cid);
		
		if (c!=null) {
			customerService.delete(c);
		}
		return "delete";
	}
	
	//5、修改操作   先根据ID查询，显示再进行修改
	public String showCustomer() {
		//使用模型驱动获取表单提交cid
		int cid = customer.getCid();
		System.out.println(cid);
		//根据ID查询
		Customer c = customerService.findOne(cid);
		//放到域对象中去
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}
	
	public String update() {
		int cid = customer.getCid();
		System.out.println(cid+"修改获取cid");
		customerService.update(customer);
		return "update";
	}
	
	
	//使用属性封装的方式获取当前页
	private Integer currentPage;
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = 1;
	}

	//6、分页方法
	public String listPage() {
		//调用service方法实现数据封装
		
		PageBean pageBean = customerService.listPage(currentPage);
		//得到封装好数据的pageBean对象，将其放入域对象中
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "listPage";
	}
	
	
	//7、条件查询的方法
	public String listcondition() {
		//如果输入客户名称，根据客户名称查询
		//如果不输入任何内容，查询所有
		if (customer.getCustName()!=null&&!"".equals(customer.getCustName())) {
			//不为空
			 list = customerService.findCondition(customer);
			//ServletActionContext.getRequest().setAttribute("list", list);
		}else {
			//没有任何内容输入
			list = customerService.findAll();
		}
		return "listcondition";
	}
	
	//8、到客户信息查询的页面
	public String toSelectCustomerPage() {
		List<Dict> listDict = customerService.findAllDictLevel();
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toSelectCustomerPage";
	}
	
	//9、综合查询，多条件查询
	public String moreCondition() {
		List<Customer> list = customerService.findMoreCondition(customer);
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		
		return "moreCondition";
	}
	
	//根据客户来源进行统计
	public String countSource() {
		List list = customerService.findCountSource();
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "countSource";
	}
	
	//根据客户级别进行统计
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
 *          佛祖保佑             永无BUG
 */  
