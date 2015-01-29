import java.util.ArrayList;
import java.util.List;

public class id3tree {
	// 编号
	  private long id;

	  // 标题
	  private String title;

	  // 下级列表
	  private List<id3tree> children = new ArrayList<id3tree>();

	  // 上级，顶层为null
	  private id3tree parent;

	  // 前一个节点
	  private id3tree prev;

	  // 后一个节点
	  private id3tree next;

	  // 当前处理的节点
	  private id3tree current;

	  /**
	   * 默认的构造器
	   */
	  public id3tree() {
	  }

	  /**
	   * 推荐使用的构造器
	   * 
	   * @param id 编号
	   * @param title 文本
	   */
	  public id3tree(long id, String title) {
	    this.id = id;
	    this.title = title;
	  }

	  /**
	   * 增加一个下属。<br>
	   * 自动对应上级和兄弟结点
	   * 
	   * @param f 被增加的节点
	   */
	  public void addChild(id3tree f) {
	    children.add(f);
	    f.setParent(this);
	    if (current != null) {
	      current.next = f;
	    }
	    f.prev = current;
	    current = f;
	  }

	  /**
	   * 输出为下拉列表的方法
	   * 
	   * @param selectedId 被选中的编号
	   * @return 下拉列表的字符串。可以直接放到<select></select>里面
	   */
	  public String getOption(long selectedId) {
	    return "<option>" + toStringOption("", "", selectedId);
	  }

	  /**
	   * 输出为Text的方法。<br>
	   * 应网友建议，更改toString为toStringText方法。
	   * 
	   * @param lftStr 左侧额外的字符串
	   * @param append 右侧显示的字符串
	   * @return 文本形式的字符串
	   */
	  public String toStringText(String lftStr, String append) {
	    StringBuilder b = new StringBuilder();
	    b.append(append + title);
	    b.append(" ");
	    if (children.size() > 0) {
	      for (int i = 0; i < children.size() - 1; i++) {
	        b.append(lftStr + children.get(i).toStringText(lftStr + "│", "├"));
	      }
	      b.append(lftStr + children.get(children.size() - 1).toStringText(lftStr + " ", "└"));
	    }
	    return b.toString();
	  }

	  public static void main(String[] args) {
	    id3tree root = new id3tree(0, "菜单列表");
	    id3tree f1 = new id3tree(1, "开始菜单");
	    root.addChild(f1);
	    id3tree f1_1 = new id3tree(11, "程序");
	    f1.addChild(f1_1);
	    id3tree f1_1_1 = new id3tree(111, "附件");
	    f1_1.addChild(f1_1_1);
	    id3tree f1_1_1_1 = new id3tree(1111, "娱乐");
	    f1_1_1.addChild(f1_1_1_1);
	    id3tree f1_1_1_2 = new id3tree(1112, "娱乐2");
	    f1_1_1.addChild(f1_1_1_2);
	    id3tree f1_2 = new id3tree(12, "辅助工具");
	    f1.addChild(f1_2);
	    id3tree f2 = new id3tree(2, "My Documents ");
	    root.addChild(f2);
	    id3tree f3 = new id3tree(3, "My Documents2 ");
	    root.addChild(f3);
	    System.out.println(root.toStringText(" ", ""));
//	    System.out.println(root.getOption(111));
//	    System.out.println(f1_1_1_2.getPrev().getTitle());
//	    System.out.println(f1_1_1_2.getPrev().getParent().getTitle());
	  }

	  public List<id3tree> getChildren() {
	    return children;
	  }

	  public long getId() {
	    return id;
	  }

	  /**
	   * 得到下一个兄弟结点。
	   * 
	   * @return 如果是最后一个，则返回null
	   */
	  public id3tree getNext() {
	    return next;
	  }

	  public id3tree getParent() {
	    return parent;
	  }

	  /**
	   * 得到前一个兄弟结点。
	   * 
	   * @return 如果是第一个，则返回null
	   */
	  public id3tree getPrev() {
	    return prev;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public void setNext(id3tree next) {
	    this.next = next;
	  }

	  public void setParent(id3tree parent) {
	    this.parent = parent;
	  }

	  public void setPrev(id3tree prev) {
	    this.prev = prev;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  /**
	   * 构造下拉列表.
	   * 
	   * @param lftStr 左侧的字符
	   * @param append 增加的字符
	   * @param idSelected 被选中的编号
	   * @return 下拉列表字符串
	   */
	  private String toStringOption(String lftStr, String append, long idSelected) {
	    StringBuilder b = new StringBuilder();
	    b.append(append + title + "</option>");
	    b.append(" ");
	    if (children.size() > 0) {
	      for (int i = 0; i < children.size() - 1; i++) {
	        b.append("<option value='" + children.get(i).getId() + "'" + (idSelected == children.get(i).getId() ? " selected" : "")
	            + ">" + lftStr + children.get(i).toStringOption(lftStr + "│", "├", idSelected));
	      }
	      b.append("<option value='" + children.get(children.size() - 1).getId() + "'"
	          + (idSelected == children.get(children.size() - 1).getId() ? " selected" : "") + ">" + lftStr
	          + children.get(children.size() - 1).toStringOption(lftStr + " ", "└", idSelected));
	    }
	    return b.toString();
	  }
}

//菜单列表
//├开始菜单
//│├程序
//││└附件
//││ ├娱乐
//││ └娱乐2
//│└辅助工具
//├My Documents 
//└My Documents2 
//
//<option>菜单列表</option>
//<option value='1'>├开始菜单</option>
//<option value='11'>│├程序</option>
//<option value='111' selected>││└附件</option>
//<option value='1111'>││ ├娱乐</option>
//<option value='1112'>││ └娱乐2</option>
//<option value='12'>│└辅助工具</option>
//<option value='2'>├My Documents </option>
//<option value='3'>└My Documents2 </option>
