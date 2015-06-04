package project_automata;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JPanel;

public class graphics_panel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final int space_for_connections=200;
	private static final int DFA_node_radius=40;
	private static final int row_dist=200;
	public static int[][] grid;
	Graphics2D g2d;
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw_graphics(g);
    }
	public void draw_graphics(Graphics g){
		grid=new int[ui_dfa.res_height/10][ui_dfa.res_width/10];
		int temp_x,temp_y,temp_x_1,temp_y_1;
		String node_name;
		g2d = (Graphics2D) g;
		BasicStroke bs2 = new BasicStroke(10, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        g2d.setStroke(bs2);
		for(int i=1;i<=structure_file.counter_1;i++){
			g2d.setPaint(structure_file.color[i-1]);
			node_name=structure_file.hm.get(i);
			temp_x=get_x_coord(i);temp_y=get_y_coord(i);
			for (int j=temp_y/10;j<temp_y/10+DFA_node_radius/10;j++)for(int k=temp_x/10;k<temp_x/10+DFA_node_radius/10;k++)grid[j][k]=1;
			g2d.drawOval(temp_x, temp_y, DFA_node_radius, DFA_node_radius);
			AttributedString as = new AttributedString(node_name);
			as.addAttribute(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD, 0, node_name.length());
			g2d.drawString(as.getIterator(), temp_x+DFA_node_radius/3,temp_y+DFA_node_radius/2);
		}
		bs2 = new BasicStroke(5, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);g2d.setStroke(bs2);
		for (int i =1;i<=structure_file.counter_1;i++){
			temp_x=get_x_coord(i);temp_y=get_y_coord(i);g2d.setPaint(structure_file.color[i-1]);
			for (int j =1;j<=structure_file.counter_1;j++){
				temp_x_1=get_x_coord(j);temp_y_1=get_y_coord(j);
				System.out.println(structure_file.transitions[i][j]);
				if (structure_file.transitions[i][j].equals("Φ"))continue;
				else{
					int port_coords_1[]=new int[2];
					int port_coords_2[]=new int[2];
					int line_coord, direction, inc, direction_1, inc_1, direction_2, inc_2, limit;
					if (temp_x==temp_x_1&&temp_y==temp_y_1){}
					else if (temp_y==temp_y_1){
						if (temp_x<temp_x_1){direction_1=DFA_node_radius/10; inc_1=-1;direction_2=0;inc_2=1;}else{direction_1=0; inc_1=1;direction_2=DFA_node_radius/10;inc_2=-1;}
						if (i<=structure_file.counter_1/2){direction=-1;inc=-1;limit=0;}else{direction=DFA_node_radius/10;inc=1;limit=ui_dfa.res_height/10;}
						port_coords_1=traverse_upper(temp_y/10+direction,temp_x/10+direction_1, inc_1);
						port_coords_2=traverse_upper(temp_y_1/10+direction,temp_x_1/10+direction_2, inc_2);
						line_coord=get_coord_upper(temp_y/10+direction+inc, inc, port_coords_2[0], limit, port_coords_1[0], inc_2);
						line_vertical(port_coords_1[1],line_coord,port_coords_1[0], inc);
						line_horizontal(port_coords_1[0],port_coords_2[0],line_coord, inc_2);
						line_vertical(line_coord,port_coords_2[1],port_coords_2[0], inc*-1);
					}
					else {
						int temp;
						if (temp_x<temp_x_1){direction_1=DFA_node_radius/10; inc_1=-1;direction_2=0;inc_2=1;}else if (temp_x>temp_x_1) {direction_1=0; inc_1=1;direction_2=DFA_node_radius/10;inc_2=-1;} else {direction_2=0;direction_1=0;inc_1=1;inc_2=1;}
						if (i<=structure_file.counter_1/2){direction=DFA_node_radius/10;inc=1;limit=temp_y_1/10-1;temp=-1;}else{direction=-1;inc=-1;limit=temp_y/10+1;temp=DFA_node_radius/10;}
						port_coords_1=traverse_upper(temp_y/10+direction,temp_x/10+direction_1, inc_1);
						port_coords_2=traverse_upper(temp_y_1/10+temp,temp_x_1/10+direction_2, inc_2);
						line_coord=get_coord_upper(temp_y/10+direction+inc, inc, port_coords_2[0], limit, port_coords_1[0], inc_2);
						line_vertical(port_coords_1[1],line_coord,port_coords_1[0], inc);
						line_horizontal(port_coords_1[0],port_coords_2[0],line_coord, inc_2);
						line_vertical(line_coord,port_coords_2[1],port_coords_2[0], inc);
					}
				}
			}
		}
	}
	public int get_x_coord(int index){if (index<=structure_file.counter_1/2){return space_for_connections+20*index+DFA_node_radius*index;}else{return space_for_connections+20*(index-structure_file.counter_1/2)+DFA_node_radius*(index-structure_file.counter_1/2);}}
	public int get_y_coord(int index){if (index<=structure_file.counter_1/2){return space_for_connections;}else{return space_for_connections+row_dist+DFA_node_radius;}}
	public int[] traverse_upper(int start,int x, int inc){for(int i=0;i<DFA_node_radius/10;i++){int port_coord[]=new int[2];port_coord[1]=start;if(grid[start][x]==0){port_coord[0]=x;return port_coord;}else x=x+inc;}return null;}
	public int[] traverse_side(int start,int x, int inc){for(int i=0;i<DFA_node_radius/10;i++){int port_coord[]=new int[2];port_coord[0]=start;if(grid[x][start]==0){port_coord[1]=x;return port_coord;}else x=x+inc;}return null;}
	public int get_coord_upper(int start, int inc, int end, int limit, int x, int dir){int flag;for(int i = 0;i<Math.abs(start-limit);i++){flag=0;for (int j=x;j!=end;j=j+dir){if(grid[start][j]==1&&grid[start][j+dir]==1){flag=1;}}if (flag==0){return start;}start=start+inc;}return 0;}
	public void line_vertical(int diff_1, int diff_2, int same, int direction){g2d.drawLine(same*10, diff_1*10, same*10, diff_2*10);if(direction==1){for (int k =diff_1;k<=diff_2;k=k+direction)grid[k][same]=2;}else{for (int k =diff_1;k>=diff_2;k=k+direction)grid[k][same]=2;}}
	public void line_horizontal(int diff_1, int diff_2, int same, int direction){g2d.drawLine(diff_1*10, same*10, diff_2*10, same*10);if(direction==1){for (int k =diff_1;k<=diff_2;k=k+direction)grid[same][k]=1;}else{for (int k =diff_1;k>=diff_2;k=k+direction)grid[same][k]=1;}}
}
