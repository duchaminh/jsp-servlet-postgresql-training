package com.hust.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hust.dao.UserDAO;
import com.hust.dao.UserDAOImpl;
import com.hust.dto.UserDTO;
import com.hust.util.ParamWithValue;

import jp.co.nobworks.openfunxion4.core.BlockLayout;
import jp.co.nobworks.openfunxion4.core.Box;
import jp.co.nobworks.openfunxion4.core.Line;
import jp.co.nobworks.openfunxion4.core.OpenFunXion;
import jp.co.nobworks.openfunxion4.core.OpenFunXionException;
import jp.co.nobworks.openfunxion4.core.Text;
/*
 * バーコードフォントは http://itext.sourceforge.net/downloads/barcodefonts.zip より取得<BR>
 * サンプルプログラムはデバッグモードをＯＮにしています。（デザインツールの設定で指定）
 * デバッグモードにすることで、ツール上での確認と同様の動作をします。<BR>
 * （確認用作業ファイルの使用、確認用コマンドの実行）
 */
@WebServlet("/ListController")
public class ListController extends HttpServlet {
   
	private static final long serialVersionUID = 5868365644675898242L;
	UserDAO userDAO = null;
	
	public ListController() {
        userDAO = new UserDAOImpl();
    }
	
	final private static String XML_FILE = "/home/hmd/eclipse-workspace/traning/src/com/hust/controller/user.xml";
     
    /*
      * Servlet で PDFファイルを生成せず、Response に直接返す場合の例
      */
    @SuppressWarnings("null")
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException {
        response.setContentType( "application/pdf" );
        response.setHeader("Content-Disposition","inline; filename=User.pdf");
        if(request.getSession().getAttribute("name") == null) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("not-authenticated.jsp");
			dispatcher.forward(request, response);
        }
        	
        List<ParamWithValue> listParam = new ArrayList<ParamWithValue>();
        listParam.add(new ParamWithValue("family_name", request.getParameter("familyName")));
        listParam.add(new ParamWithValue("first_name", request.getParameter("firstName")));
        listParam.add(new ParamWithValue("authority_name", request.getParameter("authorityName")));
        OpenFunXion ofx = new OpenFunXion( XML_FILE );
        try {
            ofx.open( response );
        } catch ( OpenFunXionException e ) {
            e.printStackTrace();
            // ToDo
            return;
        }
        if(!request.getParameter("authorityName").isEmpty())
        	makePdf( ofx , listParam);
        else {
        	List<UserDTO> users = userDAO.search(listParam);
        	List<String> listAuthorityName = new ArrayList<String>();
        	
        	for(UserDTO user : users) {
        		if(!listAuthorityName.contains(user.getAuthorityName()))
        			listAuthorityName.add(user.getAuthorityName());
        	}
        	makePdf(ofx, users, listAuthorityName);
        }
    }
    private void makePdf(OpenFunXion ofx, List<UserDTO> users, List<String> listAuthorityName) {
    	//printOutline( ofx );
        int moveY = 11 * 6;
     // 改ページ用のカウンタ
        int count;
        int No = 1;
     // デザインツールで作成した各一覧項目の取得
        Line rowLine = ofx.getLine( "row_line" );
        Text rowNo = ofx.getText( "row_no" );
        Text rowUser = ofx.getText( "row_user" );
        Text rowName = ofx.getText( "row_name" );
        Text rowSex = ofx.getText( "row_sex" );
        Text rowAge = ofx.getText( "row_age" );
        Text authority = ofx.getText( "authority_name" );
        
        // 上記は それぞれの型にあわせたメソッドで取得していますが
        // これはキャストして取得する例
        Box reverseRow = (Box)ofx.getPrintObject( "reverse_row" );
        BlockLayout dataBlock = ofx.getBlockLayout( "data_block" );
        
        //int lengthPage = listAuthorityName.size();
		//System.out.println(lengthPage);
		//int currentPage = 0;
		for(String authorityName : listAuthorityName) {
			count = 0;
			printOutline( ofx );
			authority.setMessage( authorityName);
			authority.print();
			for(UserDTO user : users) {
				if(user.getAuthorityName().equals(authorityName)) {
					
					if ( count > 0 && count % 10 == 0 ) {
		                // 改ページ
		                
		            	ofx.newPage();
		                dataBlock.resetPosition();
		                
		                // 新しいページの固定部を出力
		                printOutline( ofx );
		                authority.setMessage( authorityName);
		    			authority.print();
		  
		            }
					// それぞれのY方向位置を更新する
					
		            reverseRow.moveY( moveY );
		            
		            // 画像ファイルの位置をフルパスで指定
		            
		            rowNo.setMessage( String.valueOf(No) );
		            rowNo.print();
		            rowNo.moveY( moveY );
		            
		            rowUser.setMessage( user.getUserId() );
		            rowUser.print();
		            rowUser.moveY( moveY );
		            
		            rowName.setMessage( user.getFamilyName() +" "+ user.getFirstName() );
		            rowName.print();
		            rowName.moveY( moveY );
		            
		            rowSex.setMessage( user.getGenderName() );
		            rowSex.print();
		            rowSex.moveY( moveY );
		            
		            rowAge.setMessage( String.valueOf(user.getAge()) );
		            rowAge.print();
		            rowAge.moveY( moveY );
		            
		            rowLine.print();
		            rowLine.moveY( moveY );
		            
		            count++;
		            No++;
				}
			}
			//currentPage ++;
			//if(currentPage < lengthPage) {
				ofx.newPage();
	            dataBlock.resetPosition();
	            
	            // 新しいページの固定部を出力
	          //  printOutline( ofx );
			//}
		}
		ofx.out();
	}
	private void makePdf( OpenFunXion ofx, List<ParamWithValue> listParam ) {
        
        // 処理モデルから一覧データのコレクションを取得
    	// 大量データの場合、このように一度にもってこれません。あくまでもサンプルです。
    	List<UserDTO> users = userDAO.search(listParam);
        
        // レイアウトの固定部を出力
        printOutline( ofx );
        int moveY = 11 * 6;
     // 改ページ用のカウンタ
        int count = 0;
        int No = 1;
     // デザインツールで作成した各一覧項目の取得
        Line rowLine = ofx.getLine( "row_line" );
        Text rowNo = ofx.getText( "row_no" );
        Text rowUser = ofx.getText( "row_user" );
        Text rowName = ofx.getText( "row_name" );
        Text rowSex = ofx.getText( "row_sex" );
        Text rowAge = ofx.getText( "row_age" );
        Text authority = ofx.getText( "authority_name" );
        
        // 上記は それぞれの型にあわせたメソッドで取得していますが
        // これはキャストして取得する例
        Box reverseRow = (Box)ofx.getPrintObject( "reverse_row" );
        BlockLayout dataBlock = ofx.getBlockLayout( "data_block" );
        
        for ( Iterator<UserDTO> it=users.iterator();it.hasNext(); ) {
        	UserDTO user = (UserDTO)it.next();
        	authority.setMessage(user.getAuthorityName());
			authority.print();
            
            if ( count > 0 && count % 10 == 0 ) {
                // 改ページ
                
            	ofx.newPage();
                dataBlock.resetPosition();
                
                // 新しいページの固定部を出力
                printOutline( ofx );
  
            }
            
            // それぞれのY方向位置を更新する
            reverseRow.moveY( moveY );
            
            // 画像ファイルの位置をフルパスで指定
            
            rowNo.setMessage( String.valueOf(No) );
            rowNo.print();
            rowNo.moveY( moveY );
            
            rowUser.setMessage( user.getUserId() );
            rowUser.print();
            rowUser.moveY( moveY );
            
            rowName.setMessage( user.getFamilyName() + user.getFirstName() );
            rowName.print();
            rowName.moveY( moveY );
            
            rowSex.setMessage( user.getGenderName() );
            rowSex.print();
            rowSex.moveY( moveY );
            
            rowAge.setMessage( String.valueOf(user.getAge()) );
            rowAge.print();
            rowAge.moveY( moveY );
            
            rowLine.print();
            rowLine.moveY( moveY );
            
            count++;
            No++;
        }
        
        // 終了処理
        ofx.out();
    }
    /**
     * 外観部分の出力
     */
    public void printOutline( OpenFunXion ofx ) {
        //        ofx.print( "body_block" );
        // または
    	ofx.print( "box_1" );
        ofx.print( "title_1" );
        ofx.print( "box_2" );
        ofx.print( "text_2" );
        ofx.print( "box_3" );
        ofx.print( "text_3" );
        ofx.print( "box_4" );
        ofx.print( "box_5" );
        ofx.print( "box_6" );
        ofx.print( "box_7" );
        ofx.print( "box_8" );
        ofx.print( "text_4" );
        ofx.print( "text_55" );
        ofx.print( "text_56" );
        ofx.print( "text_58" );
        ofx.print( "text_59" );
        ofx.print( "text_9" );
        ofx.print( "box_9" );
        ofx.print( "text_10" );
        ofx.print( "box_10" );
        ofx.print( "text_11" );
        ofx.print( "box_11" );
        ofx.print( "text_12" );
        ofx.print( "box_12" );
        ofx.print( "text_13" );
        ofx.print( "box_13" );
        ofx.print( "text_14" );
        //ofx.print( "text_15" );
        ofx.print( "out_box" );
        ofx.print( "header_box" );
        ofx.print( "header_1" );
        ofx.print( "header_2" );
        ofx.print( "header_3" );
        ofx.print( "header_4" );
        ofx.print( "header_5" );
    }
    }