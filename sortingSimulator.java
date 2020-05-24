/*import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
*/


//red=i
//green=n


import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VisualSorting
{
	//Frame
	private JFrame jf;

	//General Variables
	private int len=1;
	private int off=0;
	private int curAlg=0;
	private int spd=15;
	private int compare=0;
	private int acc=0;
	private int k=0;
	private int[] c=new int[25];

	//Graph Variables
	private final int SIZE=600;
	private int current=-1;
	private int check=-1;
	private int width=SIZE/25;
	private int type=0;

	//Arrays
	private int[] list;
	private String[] types={"Bar Graph","Dot Plot"};
	private String[] algorithms={"Selection Sort",
					"Bubble Sort",
					"Cocktail sort",
					"Odd Even sort",
					"Insertion Sort",
					"Tim sort",
					"Quick sort",
					"Heap sort",
					"Merge sort",
					"Pigeonhole sort",
					"Radix sort(MSB)",
					"Bogo sort"};
	private String[] algInfo={"Comparison Based Sort\nBest Case: O(n^2)\nWorst Case: O(n^2)\nAverage Case: O(n^2)\nStable: No",
					"Comparison Based Sort\nBest Case: O(n)\nWorst Case: O(n^2)\nAverage Case: O(n^2)\nStable: Yes",
					"Comparison Based Sort\nBest Case: O(n)\nWorst Case: O(n^2)\nAverage Case: O(n^2)\nStable: Yes",
					"Comparison Based Sort\nBest Case: O(n)\nWorst Case: O(n^2)\nAverage Case: O(n^2)\nStable: Yes",
					"Comparison Based Sort\nBest Case: O(n)\nWorst Case: O(n^2)\nAverage Case: O(n^2)\nStable: Yes",
					"Comparison Based Sort\nBest Case: O(n)\nWorst Case: O(nlog n)\nAverage Case: O(nlog n)\nStable: Yes",
					"Comparison Based Sort\nBest Case: O(nlog n)\nWorst Case: O(n^2)\nAverage Case: O(nlog n)\nStable: No",
					"Comparison Based Sort\nBest Case: O(nlog n)\nWorst Case: O(nlog n)\nAverage Case: O(nlog n)\nStable: No",
					"Comparison Based Sort\nBest Case: O(nlog n)\nWorst Case: O(nlog n)\nAverage Case: O(nlog n\nStable: Yes",
					"Non-Comparison Sort\nBest Case: --\nWorst Case: O(n+2^k)\nAverage Case: O(n+2^k)\nStable: Yes",
					"Non-Comparison Sort\nBest Case: --\nWorst Case: O(n*(k/d))\nAverage Case: O(n*(k/d))\nStable: Yes",
					"Non-Comparison Sort\nBest Case: O(n)\nWorst Case: O(∞)\nAverage Case: O(n*n!)\nStable: No"};
	private String[] algData={"Algorithm selectionSort(array,size)\n{\n      repeat(size-1)times\n      set the first unsorted element as the minimum\n      for each of the unsorted elements\n      {\n            if element<currentMinimum\n                  set element as new minimum\n      }\n      swap minimum with first unsorted position\n}",
					"Algorithm bubblesort(array)\n{\n      for i<-1 to indexofLastUnsortedElement-1\n      {\n            if leftElement>rightElement\n                  swap leftElement and rightElement\n      }\n}",
					"Algorithm cocktailSort(A:list of sortable items)\n{\n      do\n      {\n            swapped:=false\n            for each i in 0 to length(a)-2 do\n            {\n                  if a[i]>A[i+1] then      //test whether the two elements are in the wrong order\n                  {\n                        swap(A[i],A[i+1]);      //let the two elements change places\n                        swapped:=true;\n                  }\n            }\n            if swapped=false then\n            {\n                  we can exit the outer loop here if no swaps ocurred.\n                  break do-while loop;\n            }\n            swapped:=false\n            for each i in length(A) -2 down to 0 do\n            {\n                  if A[i]>A[i+1] then\n                  {\n                        swap(A[i],A[i+1])\n                        swapped:=true;\n                  }\n            }\n      }while swapped;      //if no elements have been swapped,then the list is sorted\n}",
					"Algorithm oddEvenSort(n)\n{\n      id := process's label;\n      for i := 1 to n do\n      {\n            if i is odd and id is odd then\n                  compareExchange_min(id+1);\n            else\n                  compareExchange_max(id-1);\n            if i is even and id is even then\n                  compareExchange_min(id+1);\n            else\n                  compareExchange_max(id-1);\n      }\n}",
					"Algorithm insertionSort(A,n)\n{\n      for i:=2 to n do\n      {\n            j:=i-1;\n            key:=A[i];\n            while j>0 and A[j]>key do\n            {\n                  A[j+1]:=A[j];\n                  j:=j-1;\n            }\n            A[j+1]:=key;\n      }\n}",
					"Algotrithm timsort(int arr[], int n)\n{\n     //Sort individual arrays of size RUN\n     for i:=0 to n step i+RUN do\n            insertionSort(arr,i,min((i+31),(n-1)));\n      for size:=RUN to n step size*2 do\n      {\n            for left:=0 to n do left+2*size\n            {\n                  int mid:=left+size-1;\n                  int right:=min((left+2*size-1),(n-1));\n                  merge(arr,left,mid,right);\n            }\n      }\n}",
					"Algorithm quickSort(p,q)\n{\n      if (p<q) then\n      {     //divide p into two sub-problems\n            j:=partition(a,p,q+1);\n            //p is the position of partitioning element\n            //solve the sub-problems\n            quickSort(p,j-1);\n            quickSort(j+1,q);\n      }      \n}\n\nAlgorithm partition(a,m,p)\n{\n      v:=a[m];\n      i:=m;\n      j:=p;\n      repeat\n      {\n            repeat\n            i:=i+1;\n            until(a[j]>=v);\n            repeat\n                  j:=j-1;\n            until(a[j]<=v);\n            if(i<j) then interchange(a,i,j);\n      }until(i>=j);\n      a[m]:=a[j];\n      a[j]:=v;\n      return j;\n}",
					"Algorithm heapSort(int arr[],int n)\n{\n      for i:=n/2-1 to 0 step -1 do\n            heapify(arr,i,n);\n      for i:=n-1 to 0 step -1 do\n      {\n            swap(arr[0],arr[i]);\n            heapify(arr,i,0);\n      }\n}\n\nAlgorithm heapify(A,i,n)\n{\n      item:=a[i];\n      j:=2i;\n      while(j<=n)\n      {\n            if(j<n) and (a[j]<a[j+1])\n                  then j:=j+1;\n            if(item>=a[j])\n            break;\n            a[j/2]:=a[j]\n              j:=2j;\n      }\n      a[j/2]:=item;\n}",
					"Algorithm mergeSort(low,high)\n//a[low,high] is a global array to be sorted\n//small[P] is time if there is only one element to sort.In this case the list is already sorted.\n{\n      if(low<high)then//If there are more than one element\n      {     //Divide P into subproblems find where to split the set\n            mid=[(low+high)/2];\n            //Solve the subproblems\n            mergeSort(low,mid);\n            mergeSort(mid+1,high);//Combine the solutions\n            Merge(low,mid,high);\n      }\n}",
					"Algorithm pigeonHoleSort(array,size)\n{\n      //find max and min from the array list\n      holeRange:=max-min+1;\n      //define holeRange numbr of lists\n      for i:i=0 to n-1 do\n            hole[array[i]-min].append(array[i]);\n      count:=0;\n      for j:=0 to holeRange-1 do\n      {\n            while hole[j] is not empty do\n            {\n                  array[count]:=get first node;\n                  count:=count+1;\n            }\n      }\n}",
					"Algorithm radixSort(int arr[],int n)\n{\n      int m:=getMax(arr,n);//find the max number to know the number of digits\n      //Do counting sort for every digit\n      for exp:=1 to m/exp>0 do step exp*10\n            countSort(arr,n,exp);\n}\n\nAlgorithm countSort(int arr[],int n,int exp)\n{\n      int output[n];//output array\n      int i,count[10]:={0};\n      for i:=0 to n step 1 do\n            count[arr[i]/exp%10]++;\n      //change count[i] so that count[i] now contains actual position of this digit\n      for i:=1 to 10 step 1 do\n      count[i]:=count[i]+count[i-1];\n      for i:=n-1 to 0 step -1 do\n      {\n            output[count[(arr[i].exp)%10]-1]=arr[i];\n            count[(arr[i]/exp)%10]--;\n      }\n      for i:=0 to n step 1 do\n            arr[i]:=output[i];\n}",
					"Algorithm bogoSort(int a[],int n)\n{\n      //if array is not sorted then shuffle the array again\n      while(!isSorted(a,n))\n            shuffle(a,n);\n}\n\nAlgorithm shuffle(int a[],int n)\n{\n      for i:=0 to n step 1 do\n            swap(a[i],a[rand()%n]);\n}\n\nAlgorithm isSorted(int a[],int n)\n{\n      while(--n>1)\n      {\n            if(a[n]<a[n-1])\n                  return false;\n      }\n      return true;\n}"};

	private String[] gr={"Index checked for","Correct Index of Element","Correct Index of Element","Element 1 being compared","Start of unsorted list","Index checked for","Pivot Element(p)","Correct Position of Node","End of sub-array"," ","2. Sorts the array","Element 1 being checked"};
	private String[] re={"Element being compared","Element being compared","Element being compared","Element 2 being compared","Element being compared","Element being compared","Element at q(low)","Node being compared","Element being compared","Index of Sorted Element","1. Compares unit place","Random Element 2 being checked"};

	//Booleans
	private boolean sorting=false;
	private boolean shuffled=true;

	//Util Objects
	SortingAlgorithms algorithm=new SortingAlgorithms();
	Random r=new Random();

	//Panels
	JPanel tools=new JPanel();
	JPanel algo=new JPanel();
	GraphCanvas canvas;
	Color col=new Color(192,192,255);

	//Labels
	JLabel delayL=new JLabel("Delay");
	JLabel msL=new JLabel(spd+" ms");
	JLabel sizeL=new JLabel("Size");
	JLabel lenL=new JLabel(len+"");
	JLabel compareL=new JLabel("Comparisons : "+compare);
	JLabel accessL=new JLabel("Array Accesses : "+acc);
	JLabel algorithmL=new JLabel("Algorithms");
	JLabel numberL=new JLabel("Enter Number Manually");
	JLabel typeL=new JLabel("Graph Types");
	JLabel l2=new JLabel("(Less than 26)");
	JLabel green=new JLabel("Green : ");
	JLabel red=new JLabel("Red : ");
	JLabel green1=new JLabel("Index checked for");
	JLabel red1=new JLabel("Element being compared");

	//Drop Down Box
	JComboBox alg=new JComboBox(algorithms);
	JComboBox graph=new JComboBox(types);
	JTextArea information=new JTextArea(algInfo[curAlg]);
	JTextArea sortAlgorithm=new JTextArea(algData[curAlg]);
	JScrollPane scroll=new JScrollPane(sortAlgorithm,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JTextField numTF=new JTextField();

	//Buttons
	JButton sort=new JButton("Sort");
	JButton shuffle=new JButton("Random");
	//JButton Algorithm=new JButton("Algorithm");
	JButton Enter=new JButton("Enter");
//	JButton credit=new JButton("Credits");

	//Sliders
	JSlider size=new JSlider(JSlider.HORIZONTAL,1,25,1);
	JSlider speed=new JSlider(JSlider.HORIZONTAL,1,1000,spd);

	//Border Style
	Border loweredetched=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	//Constructor
	public VisualSorting()
	{
		shuffleList();	//Creates the list
		shuffleList1();
		initialize();	//Initialize the GUI
	}

	public void createList()
	{
		int rand;
		list=new int[len];	//Creates list equal to length
		for(int i=0;i<len;i++)
		{
//			list[i]=i+1;	//Fills the list from 1 to len
			rand=randomNumber(i);
			list[i]=rand;
		}
	}
	
	public int randomNumber(int i)
	{
		int flag,rand;
		do
		{
			rand=r.nextInt(26);
			flag=0;
			for(int j=i;j>=0;j--)
			{
				if(list[j]==rand)
					flag=1;
			}
		}while(flag==1); 
		return rand;
	}

	public void shuffleList()
	{
		if(len==0)
			JOptionPane.showMessageDialog(jf,"Select the size of array");		
		createList();
		for(int i=0;i<k;i++)
			c[i]=0;
		k=0;
		for(int a=0;a<500;a++)	//Shuffle runs 500 times
		{
			for(int i=0;i<len;i++)	//To access each element of the list
			{	
				int rand=r.nextInt(len);
				int temp=list[i];
				list[i]=list[rand];
				list[rand]=temp;
			}
		}
		sorting=false;
		shuffled=true;
	}
	
	public void createList1()
	{
		list=new int[25];	//Creates list equal to length
		list[0]=0;
		for(int i=0;i<k;i++)
		{
			if(c[i]!=0)
				list[i]=c[i];	//Fills the list from 1 to len
		}
	}

	public void shuffleList1()
	{
		createList1();
		len=k;
		if(len<=1)
			sort.setEnabled(false);
		if(len>1)
			sort.setEnabled(true);
		lenL.setText(len+"");
		sorting=false;
		shuffled=true;
	}
	
	public void initialize()
	{
		//Set Up Frame
		jf=new JFrame();
		jf.setSize(1025,635);
		jf.setTitle("Sorting Simulator");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setLayout(null);
		
		//Set Up Toolbar
		tools.setLayout(null);
		tools.setBounds(5,10,180,590);
		tools.setBackground(col);
		tools.setBorder(BorderFactory.createTitledBorder(loweredetched,"Controls"));

		//Set Up Algorithm Label
		algorithmL.setHorizontalAlignment(JLabel.CENTER);
		algorithmL.setBounds(40,20,100,25);
		tools.add(algorithmL);
	
		//Set Up Drop Down
		alg.setBounds(30,45,120,25);
		tools.add(alg);

		//Set Up Graph Type Label
		typeL.setHorizontalAlignment(JLabel.CENTER);
		typeL.setBounds(40,80,100,25);
		tools.add(typeL);
	
		//Set Up Graph Type Drop Down 
		graph.setBounds(30,105,120,25);
		tools.add(graph);

		//Set enter number manually label
		numberL.setBounds(5,150,200,25);
		tools.add(numberL);

		//Set Up Number Text Field 
		numTF.setBounds(30,180,120,25);
		tools.add(numTF);

		//Set number less than 26 label
		l2.setBounds(37,200,200,25);
		tools.add(l2);

		//Set Up enter number button
		Enter.setBounds(40,225,100,25);
		tools.add(Enter);

		//Set Up Delay Label
		delayL.setHorizontalAlignment(JLabel.LEFT);
		delayL.setBounds(10,270,50,25);
		tools.add(delayL);
		
		//Set Up ms Label
		msL.setHorizontalAlignment(JLabel.LEFT);
		msL.setBounds(135,270,50,25);
		tools.add(msL);

		//Set Up Speed Slider
		speed.setMajorTickSpacing(5); 
		speed.setBounds(55,270,75,25);
		speed.setBackground(col);
		speed.setPaintTicks(false);
		tools.add(speed);
	
		//Set Up Size Label 
		sizeL.setHorizontalAlignment(JLabel.LEFT);
		sizeL.setBounds(10,300,50,25);
		tools.add(sizeL);

		//Set Up Len Label
		lenL.setHorizontalAlignment(JLabel.LEFT);
		lenL.setBounds(140,300,50,25);
		tools.add(lenL);

		//Set Up Size Slider 
		size.setMajorTickSpacing(5); 
		size.setBounds(55,300,75,25);
		size.setBackground(col);
		size.setPaintTicks(false);
		tools.add(size);

		//Set Up Shuffle Button
		shuffle.setBounds(40,340,100,25);
		tools.add(shuffle);
		
		//Set Up Sort Button
		sort.setBounds(40,380,100,25);
		sort.setEnabled(false);
		tools.add(sort);

		//Set Up Comparisons Label
		compareL.setHorizontalAlignment(JLabel.LEFT);
		compareL.setBounds(10,425,200,25);
		tools.add(compareL);
		
		//Set Up Array Access Label
		accessL.setHorizontalAlignment(JLabel.LEFT);
		accessL.setBounds(10,450,200,25);
		tools.add(accessL);

		//Set up Algorithm display button
		//Algorithm.setBounds(30,460,120,25);
		//tools.add(Algorithm);

		//Set Up Info Area
		information.setBounds(10,480,160,85);
		information.setEditable(false);
		tools.add(information);

		//Set Up Credit Button
//		credit.setBounds(40,540,100,25);
//		tools.add(credit);

		//Set Up Canvas for Graph
		canvas=new GraphCanvas();
		canvas.setBounds(190,0,SIZE,SIZE);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));

		//Set up Algorithm 
		algo.setLayout(null);
		algo.setBounds(795,10,220,590);
		algo.setBackground(col);
		algo.setBorder(BorderFactory.createTitledBorder(loweredetched,"Algorithm"));

		//Set Up Green Label
		green.setHorizontalAlignment(JLabel.LEFT);
		green.setBounds(10,15,200,25);
		green.setForeground(Color.GREEN);
		algo.add(green);
		
		//Set Up Red Label
		red.setHorizontalAlignment(JLabel.LEFT);
		red.setBounds(10,55,200,25);
		red.setForeground(Color.RED);
		algo.add(red);
		
		//Set Up Green Value
		green1.setHorizontalAlignment(JLabel.LEFT);
		green1.setBounds(10,25,200,25);
		green1.setForeground(Color.GREEN);
		algo.add(green1);
		
		//Set Up Red Value
		red1.setHorizontalAlignment(JLabel.LEFT);
		red1.setBounds(10,65,200,25);
		red1.setForeground(Color.RED);
		algo.add(red1);

		//Set Up Algorithm Display
		scroll.setBounds(10,90,200,500);
//		scroll.setEditable(false);
		algo.add(scroll);

		jf.getContentPane().add(tools);
		jf.getContentPane().add(canvas);
		jf.getContentPane().add(algo);

		for(int i=0;i<10;i++)
			c[i]=0;

		//Add Action Listeners
		alg.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				curAlg=alg.getSelectedIndex();
				information.setText(algInfo[curAlg]);
				sortAlgorithm.setText(algData[curAlg]);			
				green1.setText(gr[curAlg]);
				if(curAlg==9)
				{
					green.setVisible(false);
					green1.setVisible(false);
					red.setBounds(10,25,200,25);
					red1.setBounds(10,35,200,25);
				}
				else if(curAlg==10)
				{
					green.setVisible(false);
					green1.setForeground(Color.RED);
					green1.setBounds(10,45,200,25);
					red.setBounds(10,25,200,25);
					red1.setBounds(10,35,200,25);
				}
				else
				{
					green.setVisible(true);
					green1.setVisible(true);
					green1.setForeground(Color.GREEN);
					green1.setBounds(10,25,200,25);
					red.setBounds(10,55,200,25);
					red1.setBounds(10,65,200,25);				
				}					
				red1.setText(re[curAlg]);
			}
		});

		graph.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				//canvas.setType(graph.getSelectedIndex());
				type=graph.getSelectedIndex();
				shuffleList();
				reset();
				Update();
			}
		});
	
		sort.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(shuffled)
				{
					sorting=true;
					compare=0;
					acc=0;
				}
			}
		});

		shuffle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shuffleList();
				reset();
			}
		});

		speed.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				spd=(int)speed.getValue();
				msL.setText(spd+" ms");
			}
		});

		size.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int val=size.getValue();
				if(size.getValue()==5)
					val=4;
				len=val;
				if(len<=1)
					sort.setEnabled(false);
				if(len>1)
					sort.setEnabled(true);
				lenL.setText(len+"");
				if(list.length != len)
					shuffleList();
				reset();
			}
		});
		
		Enter.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int flag=0;
				try
				{
					c[k]=Integer.parseInt(numTF.getText());
					numTF.setText("");
					if(c[k]>25)
					{
						JOptionPane.showMessageDialog(jf,"Element cannot be greater than 25");
						flag=1;
					}
					if(c[k]<=0)
					{
						JOptionPane.showMessageDialog(jf,"Element must be greater than 0");
						flag=1;
					}					
/*					if(k>=len)
					{
						JOptionPane.showMessageDialog(jf,"Maximum size reached");
						flag=1;
					}
*/					if(flag==0)					
					{
						k++;
						shuffleList1();	
						reset();
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(jf,"Please enter integers");
				}		
//					if(k<10)
//					{
					
//					}
/*					else
					{
						if(c[k]>len+1)
							JOptionPane.showMessageDialog(jf,"Element cannot be greater than "+(len+1));
						else
						{
							k++;
							shuffleList1();	
							reset();
						}
					}
						
*/			}
		});

/*		Algorithm.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				curAlg=alg.getSelectedIndex();
				JOptionPane.showMessageDialog(jf,algData[curAlg]);			
			}
		});

		credit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(jf, "Visual Sort\n"+"Chetan Pradhan(8815)\n"+"Mani Sharma(8828)\n"+"Shrayashri Mondal(8855)\n"+"Build Date: January 15,2020	","Credit",JOptionPane.PLAIN_MESSAGE,new ImageIcon(""));			
			}
		});
*/
		sorting();
	}

	//Sorting State
	public void sorting()
	{
		if(sorting)
		{
			try
			{
				switch(curAlg)	//Current Algorithm is Executed
				{
					case 0: algorithm.selectionSort();
						break;
					case 1: algorithm.bubbleSort();
						break;
					case 2: algorithm.cocktailSort();
						break;
					case 3: algorithm.oddEvenSort();
						break;
					case 4: algorithm.insertionSort(0,len-1);
						break;
					case 5: algorithm.timSort(len);
						break;
					case 6: algorithm.quickSort(0,len-1);
						break;
					case 7: algorithm.heapSort();
						break;
					case 8: algorithm.mergeSort(0,len-1);
						break;
					case 9: if(len!=25)
							JOptionPane.showMessageDialog(jf,"PigeonHole Sort requires 25 elements");
						else
							algorithm.pigeonholeSort();
						break;
					case 10:algorithm.radixSort(len);
						break;
					default:algorithm.bogoSort();
						break;
				}
			}
			catch(IndexOutOfBoundsException e)//Expeption handler in case list access is out of bounds
			{
			} 
		}
		reset();
		pause();	//Go into pause state
	}

	//Pause State
	public void pause()
	{
		int i=0;
		while(!sorting)	//Loop runs until sorting starts
		{
			i++;
			if(i>100)
				i=0;
			try
			{
				Thread.sleep(1);
			}
			catch(Exception e)
			{
			}
		}
		sorting();	//Exit the loop and start sorting
	}

	//Reset Some Variables
	public void reset()
	{
		sorting=false;
		current=-1;
		check=-1;
		off=0;
		Update();
	}

	//Delay Method
	public void delay()
	{
		try
		{
			Thread.sleep(spd);
		}
		catch(Exception e)
		{
		}
	}

	//Updates the Graph and Labels
	public void Update()
	{
		width=SIZE/25;
		canvas.repaint();
		compareL.setForeground(Color.blue);
		compareL.setText("Comparisons :     "+compare);
		accessL.setForeground(Color.blue);
		accessL.setText("Array Accesses : "+acc);
	}

	//Main Method
	public static void main(String[] args)
	{
		new VisualSorting();
	}

	//Sub Class to Control the Graph
	class GraphCanvas extends JPanel
	{
		public GraphCanvas()
		{
			setBackground(Color.black);
		}

		//Paint the Graph
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int i=0;i<len;i++)	//Runs through each element of the list
			{
				int HEIGHT=list[i]*(width);	//Sets the height of the element on the graph
				String text=Integer.toString(list[i]);
				int fontSize=(int)((300/width));
				if(type==0)	//Bar Graph Type
				{	
					g.setColor(Color.white);	//Default Color
					if(current>-1 && i==current)
					{
						g.setColor(Color.green);//Color of current element
					}
					if(check>-1 && i==check)
					{
						g.setColor(Color.red);//Color of checking element
					}
					//Draws the Bar and the Black Outline

					g.fillRect(i*width,SIZE-HEIGHT,width,HEIGHT);
					g.setColor(Color.black);
					g.drawRect(i*width,SIZE-HEIGHT,width,HEIGHT);
					g.setFont(new Font("SansSerif",Font.BOLD,fontSize));
					g.drawString(text,(width*i)+((width/2)-(width/4)),(SIZE-(HEIGHT))+((width/2)+(width/4)));
				}
				else if(type==1)	//Dot Plot Type
				{	
					g.setColor(Color.white);	//Default Color
					if(current>-1 && i==current)
					{
						g.setColor(Color.green);//Color of current element
					}
					if(check>-1 && i==check)
					{
						g.setColor(Color.red);//Color of checking element
					}
					//Draws the Dot and the Black Outline
					g.fillOval(i*width,SIZE-HEIGHT,width,width);
					g.setFont(new Font("SansSerif",Font.BOLD,fontSize));
					g.setColor(Color.black);
					g.drawString(text,(width)*(i)+((width/2)-(width/4)),(SIZE-(HEIGHT))+((width/2)+(width/4)));
				}
			}
		}
	}

	//Sub Class for Algorithms
	class SortingAlgorithms
	{
		public void selectionSort()
		{
			int c=0;
			while(c<len && sorting)
			{
				int sm=c;
				current=c;
				for(int i=c+1;i<len;i++)
				{
					if(!sorting)	
						break;
					if(list[i]<list[sm])
					{
						sm=i;
					}
					check=i;
					compare++;
					acc+=2;
					Update();
					delay();
				}
				if(c!=sm)
					swap(c,sm);
				c++;
			}
		}
		
		public void insertionSort(int start,int end)
		{
			for(int i=start+1;i<=end;i++)
			{
				current=i;
				int j=i;
				while(list[j]<list[j-1] && sorting)
				{
					swap(j,j-1);
					check=j-1;
					compare++;
					acc+=2;
					Update();
					delay();
					if(j>start+1)
						j--;
				}
			}
		}

		public void bubbleSort()
		{
			int c=1;
			boolean noswaps=false;
			while(!noswaps && sorting)
			{
				current=len-c;
				noswaps=true;
				for(int i=0;i<len-c;i++)
				{
					if(!sorting)	
						break;
					if(list[i+1]<list[i])
					{
						noswaps=false;
						swap(i,i+1);
					}
					check=i+1;
					compare++;
					acc+=2;
					Update();
					delay();
				}
				c++;
			}
		}
		
		public void oddEvenSort()
		{
			int c=0;
			boolean noswaps=false;
			while(!noswaps && sorting)
			{
				noswaps=true;
				for(int i=0+off;i<len-1;i+=2)
				{
					if(!sorting)
						break;
					if(list[i+1]<list[i])
					{
						noswaps=false;
						swap(i,i+1);
					}
					current=i;
					check=i+1;
					compare++;
					acc+=2;
					Update();
					delay();
				}
				off=1-off;
				c++;
			}
		}
		
		public void cocktailSort()
		{
			int c=0;
			boolean noswaps=false;
			while(!noswaps && sorting)
			{
				noswaps=true;
				int i;
				int target;
				int inc;
				if(off==1)
				{
					i=len-2-c;
					target=c-1;
					inc=-1;
				}
				else
				{
					i=c;
					target=len-2-c;
					inc=1;
				}
				current=target+1;
				while(i!=target && sorting)
				{
					if(list[i]>list[i+1])
					{
						noswaps=false;
						swap(i,i+1);
					}
					check=i+1-off;
					compare++;
					acc+=2;
					Update();
					delay();
					i+=inc;
				}
				if(off==1)
					c++;
				off=1-off;
			}
		}
			
		public void heapSort()
		{
			heapify(len);
			int end=len-1;
			while(end>0 && sorting)
			{
				current=end;
				swap(end,0);
				end--;
				siftDown(0,end);
				Update();
				delay();
			}
		}

		public void heapify(int n)
		{
			int start=iParent(n-1);
			while(start>=0 && sorting)
			{
				siftDown(start,n-1);
				start--;
				Update();
				delay();
			}
		}

		public void siftDown(int start,int end)
		{
			int root=start;
			while(iLeftChild(root)<=end && sorting)
			{
				int child=iLeftChild(root);
				int swap=root;
				check=root;
				if(list[swap]<list[child])
				{
					swap=child;
				}
				if(child+1<=end && list[swap]<list[child+1])
				{
					swap=child+1;
				}
				if(swap==root)
				{
					return;
				}
				else
				{
					swap(root,swap);
					check=root;
					root=swap;
				}
				compare+=3;
				acc+=4;
				Update();
				delay();
			}
		}
		
		public int iParent(int i)
		{
			return ((i-1)/2);
		}

		public int iLeftChild(int i)
		{
			return 2*i+1;
		}

		public void quickSort(int lo,int hi)
		{
			if(!sorting)
				return;
			current=hi;
			if(lo<hi)
			{
				int p=partition(lo,hi);
				quickSort(lo,p-1);
				quickSort(p+1,hi);
			}
		}

		public int partition(int lo,int hi)
		{
			int pivot=list[hi];
			acc++;
			int i=lo-1;
			for(int j=lo;j<hi;j++)
			{
				check=j;
				if(!sorting)
					break;
				if(list[j]<pivot)
				{
					i++;
					swap(i,j);
				}
				compare++;
				acc++;
				Update();
				delay();
			}
			swap(i+1,hi);
			Update();
			delay();
			return i+1;
		}

		void merge(int l,int m,int r)
		{
			int n1=m-l+1;
			int n2=r-m;
			int L[]=new int [n1];
			int R[]=new int [n2];
			for(int i=0;i<n1;i++)
			{
				L[i]=list[l+i];
				acc++;
			}
			for(int j=0;j<n2;j++)
			{
				R[j]=list[m+1+j];
				acc++;
			}
			int i=0,j=0;
			int k=l;
			while(i<n1 && j<n2 && sorting)
			{
				check=k;
				if(L[i]<=R[j])
				{
					list[k]=L[i];
					acc++;
					i++;
				}
				else
				{
					list[k]=R[j];
					acc++;
					j++;
				}
				compare++;
				Update();
				delay();
				k++;
			}
			while(i<n1 && sorting)
			{
				list[k]=L[i];
				acc++;
				i++;
				k++;			
				Update();
				delay();
			}
			while(j<n2 && sorting)
			{
				list[k]=R[j];
				acc++;
				j++;
				k++;			
				Update();
				delay();
			}
		}
		public void mergeSort(int l,int r)
		{
			if(l<r)
			{
				int m=(l+r)/2;
				current=r+1;
				mergeSort(l,m);
				mergeSort(m+1,r);
				merge(l,m,r);
			}
		}

		public void pigeonholeSort()
		{
			int mi=0;
			int size=len-mi+1;
			int[] holes=new int[size];	
			for(int x:list)
			{
				holes[x-mi]+=1;
			}
			int i=0;
			for(int count=0;count<size;count++)
			{
				while(holes[count]>0 && sorting)
				{
					holes[count]--;
					check=i;
					list[i]=count+mi;
					acc++;
					i++;
					Update();
					delay();
				}
			}
		}

		public void radixSort(int n)
		{
			int m=getMax(n);
			for(int exp=1;m/exp>0;exp*=10)
			{
				if(!sorting)
					break;
				countSort(n,exp);
				Update();
				delay();
			}
		}
		
		public void countSort(int n,int exp)
		{
			int output[]=new int[n];
			int i;
			int count[]=new int[10];
			Arrays.fill(count,0);
			for(i=0;i<n;i++)
			{
				count[(list[i]/exp)%10]++;
				acc++;
			}
			for(i=1;i<10;i++)
			{
				count[i]+=count[i-1];
			}
			for(i=n-1;i>=0;i--)
			{
				output[count[(list[i]/exp)%10]-1]=list[i];
				count[(list[i]/exp)%10]--;
				acc++;
			}
			for(i=0;i<n;i++)
			{
				if(!sorting)
					break;
				check=i;
				list[i]=output[i];
				acc++;
				Update();
				delay();
			}
		}
		public int getMax(int n)	
		{
			int mx=list[0];
			for(int i=1;i<n;i++)
			{
				if(list[i]>mx)
				mx=list[i];
				compare++;
				acc++;
			}
			return mx;
		}
			
		public void timSort(int n)
		{
			int RUN=64;
			if(len>64)
			{		
				while(((double)len/RUN)%2!=0)
				RUN--;
			}
			for(int i=0;i<n;i+=RUN)		
			{
				insertionSort(i,Math.min((i+RUN-1),(n-1)));
			}
			for(int size=RUN;size<n;size=2*size)
			{
				for(int left=0;left<n;left+=2*size)
				{
					int mid=left+size-1;
					int right=Math.min((left+2*size-1),(n-1));
					merge(left,mid,right);
				}
				if(!sorting)
					break;
			}
		}

		public void bogoSort()
		{
			while(checkSorted()==false && sorting)
			{
				for(int i=0;i<len;i++)
				{
					if(!sorting)
						break;
					current=i;
					int rand=r.nextInt(len);
					check=rand;
					int temp=list[i];
					acc++;
					list[i]=list[rand];
					acc+=2;
					list[rand]=temp;
					acc++;
					Update();
					delay();
				}
			}
		}	

/*
		public void bogoSort()
		{
			while(checkSorted()==false)
			{
				shuffle1();
				Update();
				delay();
			}
		}
		void shuffle1()
		{
			for(int i=0;i<len;i++)
				swap(i,(int)(Math.random()*i));
			
		}*/			
		//Swapping Method
		public void swap(int i1,int i2)
		{
			int temp=list[i1];
			acc++;
			list[i1]=list[i2];
			acc+=2;
			list[i2]=temp;
			acc++;
		}
	
		public boolean checkSorted()
		{
			for(int i=0;i<len-1;i++)
			{
				if(list[i]>list[i+1])
				{
					acc+=2;
					return false;
				}
			}
			return true;
		}
	}
}
