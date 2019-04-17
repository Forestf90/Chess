package chess;

public enum SideColor {
 WHITE , BLACK;
 
 public SideColor swapColor() {
	 if(this ==SideColor.WHITE) return SideColor.BLACK;
	 return  SideColor.WHITE;
 }
}
