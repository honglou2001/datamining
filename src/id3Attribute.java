
public class id3Attribute {
	long  parentfigure ;
	long  attributeFigure ;
   String attributeContent;
   int  category ;
   
   public int getCategory() {
	return category;
}
public void setCategory(int category) {
	this.category = category;
}
public long getAttributeParentFigure() {
	return parentfigure;
	}
	public void setAttributeParentFigure(long  attributeParentFigure) {
		this.parentfigure = attributeParentFigure;
	}
	
   public String getAttributeContent() {
		return attributeContent;
	}
	public void setAttributeContent(String attributeContent) {
		this.attributeContent = attributeContent;
	}


	public long getAttributeFigure() {
		return attributeFigure;
	}
	public void setAttributeFigure(long  attributeFigure) {
		this.attributeFigure = attributeFigure;
	}
}
