# Hello World #

Copy the following code into a file HelloWorld.sawa, in the Examples folder:
```
package Examples;

public class HelloWorld {
    public static scripts

    header(String s) = {System.out.println("\n"+s)}
    
    show(String s) 
    = int i:
      for(i=0; i<s.length(); i++)
    ; {System.out.print(s.charAt(i))}
    
    hello          = show("Hello ")
    world          = show("World!")

    test1          = hello ; world
    test2          = hello ; world
    test3          = hello ; world
    
    test0 = {System.out.println("Hello World!")}
    
    main (String args[]) = header("1:"); test1; 
                           header("2:"); test2; 
                           header("3:"); test3
}
```

Compile and execute it.
  * Change the semicolons in test2 and test3 into + and &. Try again.
  * Change the semicolon in show into + and thereafter &.
  * Something will go wrong. Try to repair by declaring the loop variable private in the loop, using `private i:`

# Bag Simulation #

  * Optionally: install the GUI painter from http://www.cloudgarden.com/jigloo/:
    * You should use the Update Manager in Eclipse to download and install Jigloo - just open it (under "Help->Software Updates->Find and Install"). You will need to create a new remote site entry in the update manager for the Jigloo update-site - the url is: http://cloudgarden1.com/update-site

Create a file Examples/BasicBagFrame.java with contents like:
```

package Examples;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BasicBagFrame extends javax.swing.JFrame {
	public JToolBar toolBar;
	public JButton bPlusButton;
	public JButton bMinusButton;
	public JLabel b_Label;
	public JButton aPlusButton;
	public JLabel a_Label;
	public JButton aMinusButton;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicBagFrame inst = new BasicBagFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public BasicBagFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			this.setMinimumSize(new java.awt.Dimension(551, 367));
			   {
				toolBar = new JToolBar();
				getContentPane().add(toolBar, BorderLayout.NORTH);
				FlowLayout jToolBar1Layout = new FlowLayout();
				toolBar.setLayout(jToolBar1Layout);
				toolBar.setPreferredSize(new java.awt.Dimension(392, 40));
				{
					aPlusButton = new JButton();
					toolBar.add(aPlusButton);
					aPlusButton.setText("A+");
					aPlusButton.setEnabled(false);
					aPlusButton.setFocusable(false);
				}
				{
					a_Label = new JLabel();
					toolBar.add(a_Label);
					a_Label.setMinimumSize(new Dimension(70,14));
					a_Label.setPreferredSize(new java.awt.Dimension(35, 14));
				}
				{
					aMinusButton = new JButton();
					toolBar.add(aMinusButton);
					aMinusButton.setText("A-");
					aMinusButton.setEnabled(false);
					aMinusButton.setFocusable(false);
				}
				{
					bPlusButton = new JButton();
					toolBar.add(bPlusButton);
					bPlusButton.setText("B+");
					bPlusButton.setEnabled(false);
					bPlusButton.setFocusable(false);
				}
				{
					b_Label = new JLabel();
					toolBar.add(b_Label);
					b_Label.setMinimumSize(new Dimension(70,14));
					b_Label.setPreferredSize(new java.awt.Dimension(69, 14));
				}
				{
					bMinusButton = new JButton();
					toolBar.add(bMinusButton);
					bMinusButton.setText("B-");
					bMinusButton.setEnabled(false);
					bMinusButton.setFocusable(false);
				}
			}
			pack();
			this.setSize(551, 367);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

Create a file Examples/BagFrame.sawa with contents

```

/* Copyright (c) 1996, Delftware Technology BV. All rights reserved.
 * Granted to use and modify this software as long as this copyright
 * notice is retained.
 */
package Examples;
import static java.lang.Math.*;
import static scriptic.util.Scripts.*;
import scriptic.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.MouseEvent;

public class BagFrame extends BasicBagFrame {

    public boolean confirmExit() {
        return JOptionPane.OK_OPTION
            == JOptionPane.showConfirmDialog(this,
                 "Are you sure?", "Click OK to exit", JOptionPane.OK_CANCEL_OPTION);
  	}
                            

    int nA, nB;
    
    void refresh() {a_Label.setText(""+nA);b_Label.setText(""+nB);}
    
    void sleep() {try{Thread.sleep(0);} catch(Exception e){}}
    
    //////////////////////////////////////////////
    public static scripts
    //////////////////////////////////////////////
    
        main(String args[]) = BagFrame b = new BagFrame(): 
                              @swing: {b.setVisible(true)}
                            < b.live  
                            > @swing: {b.setVisible(false)}
        
        
    //////////////////////////////////////////////
    public scripts
    //////////////////////////////////////////////

	action(JButton button) =
		  ( AnchorActionListener a = new AnchorActionListener() : 
			@swing: {button.addActionListener(a); button.setEnabled(true)}
        < @a: {. a.event.getSource()!=null??; a.event.setSource(null); sleep() .}
        > @swing: {button.removeActionListener(a);
                   if (button.getActionListeners().length==0) {button.setEnabled(false);}}
        )
    
        key(char c) = key(this, c?!)

            exitCmd = windowClosing(this)  + key('x' !)
           aPlusCmd = action( aPlusButton) + key('A' !)
           bPlusCmd = action( bPlusButton) + key('B' !)
          aMinusCmd = action(aMinusButton) + key('a' !)
          bMinusCmd = action(bMinusButton) + key('b' !)

     aPlus  =  aPlusCmd; @swing: {:nA++; refresh():}
     bPlus  =  bPlusCmd; @swing: {:nB++; refresh():}
     aMinus = aMinusCmd; @swing: {:nA--; refresh():}
     bMinus = bMinusCmd; @swing: {:nB--; refresh():}
          
      bag   = aPlus; (bag&aMinus)
            
      exit          =  exitCmd; 
                       boolean  doExit: 
                       @swing: {doExit = confirmExit()}; 
                       while (! doExit)

      live          = bag
                   || exit

}
```
This is a simulation of a Bag, or Multiset. The bag has been specified using a recursive specification with parallelism.
It can currently only hold elements "a".
Compile and execute it.

### The overriding script "action" ###
The script `action(JButton button)` from `scriptic.utils.Scripts` has been "overridden" here, to prevent  a single button click event to be used for all listeners. Find out what happens if you comment out this overriding definition.
Change back again.

### Add support for elements "b" ###

Change the script `bag` so that it can also hold elements "b".

### Parametrization instead of parallelism ###

An alternative recursive specification would not use parallelism, but a parameterization instead:
```
  bag(int a, int b) = .......
```
Complete and test this approach. Do not use the variables nA and nB here; for now, these are only used to display the contents in the GUI.

### Explicit loop instead of recursion ###

A third approach would be with the `...` iterator, instead of the recursion. The bag script would not be parameterized any more; create it using the variables nA and nB.

# Sieve #

Study the specification of the Sieve of Eratosthenes, from the Subscript site: http://code.google.com/p/subscript/wiki/SieveOfEratosthenes

There is no implementation for the Subscript language yet; with Scriptic you can do much of the same.

Program the Sieve in Scriptic, by completing the following class:
```

package Examples;

public class Sieve {
	
	static boolean doInfo = !true;
      
	static void show  (String s) {System.out.println(s);}
	static void info  (String s) {if (doInfo) show(s);}

        static void delay() {try{Thread.sleep(0);} catch(Exception e){}}
	
  public static scripts
    
    showNum(int i)   = {show("Found: "+i)}
    
    main(String[] args) = ######

    generator(int start, int end) = while(pass<end-start); s(0,start+pass)
    
    printer      = ..; int i: r(-1, i?); showNum(i)
    
    sieve(int n) = ######
    
    s(int c, int i),
    r(int cc, int ii) >= {? if (cc!=c) {success = Boolean.FALSE;break;}
                            if (ii?)ii=i; else if (ii!=i) {success = Boolean.FALSE;break;}
    	                    info("c: "+c+"-"+i); 
                            delay()
                         ?}  

}
```
Replace ###### by appropriate code.
Note that communication is by calling s(int c, int i) and r(int cc, int ii). The first parameter is the channel index; it is 0 for the output of the generator, and the input of the first sieve. The printer listens on channel -1.

# Lifeframe improvement #

The LifeFrame program may be improved.

In case the canvas is empty, one may want to disable the Clear button.
However, the canvas is affected by several kinds of actions:
- Clear and Randomize
- Single step and Multistep
- Mouse operations

Try some simple options to change the behaviour in the desired direction.

## Second Improvement ##
A bit more complicated maybe: at times, multistepping ends in a steady state. In such a case, it may be good to disable the Step and Stop buttons. How should the program be adapted?


```










```

# Cheat sheet #
Cheat sheet for the Bag and Sieve assignments.
Don't give it up too early
```












      bag   = aPlus; (bag&aMinus)
            + bPlus; (bag&bMinus)
        














    
      bag(int a, int b) =          aPlus ; bag(a+1,b)
                        +          bPlus ; bag(a,b+1)
                        + if (a>0)(aMinus; bag(a-1,b))
                        + if (b>0)(bMinus; bag(a,b-1))













      bag1 = ...; ( aPlus
                  + bPlus 
                  + if (nA>0)aMinus
                  + if (nB>0)bMinus)













    main(String[] args) = generator(2,10000000) & (..&sieve(pass)) & printer


















    
    sieve(int n) = int p: r(n,p?); s(-1, p); (int i: ..; r(n,i?); if (i%p!=0) s(n+1,i) )









Hint for the first LifeFrame improvement, read page 23 of "Introducing Scriptic.doc"; make a guarding script like "whenSearchTextPresent"

The second LifeFrame improvement is still an open issue.

```