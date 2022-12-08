import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Serial_temp_humi extends Thread  {
	Main m;
	
	int x = 60;
	int y = 475;
	
	static int c;
	static float input_temperature;
	static float input_humidity;
	
    public Serial_temp_humi(Main m)
    {
    	this.m = m;
    	start();
    }
    
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                	if(c == 0) {
                		if(len == 1) {
                    		String temp = new String(buffer,0,len);
                    		input_temperature = 10 * Float.parseFloat(temp);
                    	}
                    	if(len == 6) {
                    		String temp = new String(buffer,0,len-2);
                    		input_temperature += Float.parseFloat(temp);
                    		System.out.println(input_temperature);
                    	}
                	}
                	else if(c == 1) {
                		if(len == 1) {
                    		String temp = new String(buffer,0,len);
                    		input_humidity = 10 * Float.parseFloat(temp);
                    	}
                    	if(len == 6) {
                    		String temp = new String(buffer,0,len-2);
                    		input_humidity += Float.parseFloat(temp);
                    		System.out.println(input_humidity);
                    	}
                	}
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }


    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        public void run ()
        {
            try
            {
                this.out.write(c);
                System.out.println(c);
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
    public void run() {
    	try
        {
    		System.out.println("in");
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM20");
            System.out.println("portIdentifier");
            if ( portIdentifier.isCurrentlyOwned() )
            {
                System.out.println("Error: Port is currently in use");
            }
            else
            {

                CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
                System.out.println("portopen");
                if ( commPort instanceof SerialPort )
                {

                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                    OutputStream out = serialPort.getOutputStream();
                    InputStream in = serialPort.getInputStream();
                    while(true) {
                        c = 0;
                        (new Thread(new SerialWriter(out))).start();
                        (new Thread(new SerialReader(in))).start();
                        Thread.sleep(1000);
                        m.temp.setText(Float.toString(input_temperature));
                        int temp = 100 -((int) input_temperature);
                        m.stick1.setBounds(x+50, y+210 + temp * 4, 200, 4*((int)input_temperature));
                        c = 1;
                        (new Thread(new SerialWriter(out))).start();
                        (new Thread(new SerialReader(in))).start();
                        Thread.sleep(1000);
                        m.humi.setText(Float.toString(input_humidity));
                        int humi = 100 -((int) input_humidity);
                        m.stick2.setBounds(x+375, y+305 + humi * 4, 250, 400);
            		}

                }
                else
                {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }     
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }
}
