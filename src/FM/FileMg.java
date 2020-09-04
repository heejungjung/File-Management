package FM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FileMg {
	FileMg fc = new FileMg();
	public static void main(String[] args) {
		while (true) {
			System.out.println();
			System.out.println("============== 파일 관리 프로그램 ==============");
			System.out.println("1. 디렉토리 생성   2. 디렉토리 삭제");			
			System.out.println("3. 파일 생성   4. 파일 삭제  5. 파일 내용 추가  6. 파일 목록 보기   7. 파일 내용 보기");
			System.out.println("8. 종료");
			System.out.println("================================================");
			System.out.print("메뉴를 입력하세요 : ");
			
			Scanner scn = new Scanner(System.in);
			int num=0;
			try {
				num = scn.nextInt();
				if(!(num>0 && num<9)){ //1~7외의 숫자가 입력되면 예외 강제 발생
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("입력된 값이 잘못되었습니다. [1-8] 메뉴늘 선택해주세요!");
			}

			switch (num) {
			case 1://디렉토리 생성
				Scanner cd = new Scanner(System.in);

				System.out.println("디렉토리 명을 입력해주세요.");
				System.out.print("▶이름 : ");
				String cdname = cd.nextLine();
				File Folder = new File("D:\\"+cdname);//폴더 경로
				if (!Folder.exists()) {
					try{
					    Folder.mkdir(); //폴더를 생성합니다.
					    System.out.println("폴더가 생성되었습니다.");
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("이미 폴더가 생성되어 있습니다.");
				}
				break;
			case 2://디렉토리 삭제
				Scanner dd = new Scanner(System.in);

				System.out.println("디렉토리 명을 입력해주세요.");
				System.out.print("▶이름 : ");
				String ddname = dd.nextLine();
				Folder = new File("D:\\"+ddname);//폴더 경로
				if (Folder.exists()) {
					try{
					    Folder.delete(); //폴더를 삭제합니다.
					    System.out.println("폴더가 삭제되었습니다.");
				        }
					catch(Exception e){
					    e.getStackTrace();
				    }
				}else {System.out.println("해당 폴더가 존재하지 않습니다.");}
				break;
			case 3://파일 생성
				try {
					Scanner cf = new Scanner(System.in);

					System.out.println("생성할 파일명을 입력해주세요.");
					System.out.print("▶이름 : ");
					String cfname = cf.nextLine();
					System.out.println("파일 내용을 입력해주세요.");
					System.out.print("▶내용 : ");
					String cfcontents = cf.nextLine();
					
					Folder = new File("D:\\"+cfname+".txt");
					FileWriter fw = new FileWriter(Folder,true);
					
					fw.write(cfcontents);
					fw.flush();
					fw.close();
					System.out.println("파일이 생성되었습니다.");
				} catch (IOException e) {
					e.printStackTrace();		
				}
				break;
			case 4://파일 삭제
				try {
					Scanner df = new Scanner(System.in);

					System.out.println("생성할 파일명을 입력해주세요.");
					System.out.print("▶이름 : ");
					String dfname = df.nextLine();
					
					Folder = new File("D:\\"+dfname+".txt");
					
					if (Folder.exists()) {
						Folder.delete(); //파일을 삭제합니다.
						System.out.println("파일이 삭제되었습니다.");
					}else {System.out.println("해당 파일이 존재하지 않습니다.");}
				}catch (Exception e) {
					e.printStackTrace();		
				}
				break;
			case 5://파일 내용 추가
				try {
					Scanner uf = new Scanner(System.in);

					System.out.println("수정할 파일명을 입력해주세요.");
					System.out.print("▶이름 : ");
					String ufname = uf.nextLine();
					System.out.println("추가할 내용을 입력해주세요.");
					System.out.print("▶내용 : ");
					String ufcontents = uf.nextLine();
					
					Folder = new File("D:\\"+ufname+".txt");
					
					if (Folder.exists()) {
						String fileText = ReadFileText(Folder);
						BufferedWriter buffWrite = new BufferedWriter(new FileWriter(Folder));
						ufcontents = fileText + "\r\n" + ufcontents;
						
						//파일쓰기
						buffWrite.write(ufcontents,0,ufcontents.length());
						//파일닫기
						buffWrite.flush();
						buffWrite.close();						

						System.out.println("파일이 수정되었습니다.");
					}else {System.out.println("해당 파일이 존재하지 않습니다.");}
				} catch (IOException e) {
					e.printStackTrace();		
				}
				break;
			case 6://파일 목록 보기
				try {
					Folder = new File("D:\\");
					File [] fileList = Folder.listFiles();
					
					for(File file:fileList) {
						if(file.isFile()) {
							String fileName = file.getName();
							System.out.println(fileName);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();		
				}
				break;
			case 7://파일 내용 보기
				try {
					Scanner rf = new Scanner(System.in);

					System.out.println("읽어 올 파일명을 입력해주세요.");
					System.out.print("▶이름 : ");
					String rfname = rf.nextLine();
					rf.close();
					Folder = new File("D:\\"+rfname+".txt");
					FileInputStream fis = new FileInputStream("D:\\"+rfname+".txt");
					
					if (Folder.exists()) {
						InputStreamReader isr = new InputStreamReader(fis);
						int fisRead = isr.read();
						while(fisRead!=-1) {
							System.out.print((char)fisRead);
							fisRead = isr.read();
						}
						isr.close();
						fis.close();
					}else {System.out.println("해당 파일이 존재하지 않습니다.");}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 8://프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}//switch()
		}//while
	}//main
	private static String ReadFileText(File file) {
		String strText = "";
		int nBuffer;
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(file));
			while((nBuffer = buffRead.read())!=-1) {
				strText+=(char)nBuffer;
			}
			buffRead.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return strText;
	}
}//class
