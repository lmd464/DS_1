
public enum MessageID {

	// Message IDs for Notices :
	Notice_StartProgram, 
	Notice_EndProgram, 
	Notice_StartMenu, 
	Notice_EndMenu, 
	Notice_InputStack, 
	Notice_DelStack, 
	Notice_ShowStack, 
	
	// Message IDs for Shows :
	Show_StartBottom, 	// Bottom 에서 시작
	Show_StartTop, 		// Top 에서 시작
	Show_EndTop, 		// Top 에서 End
	Show_EndBottom, 		// Bottom 에서 End
	
	// Message IDs for Errors :
	Error_WrongMenu, 
	Error_InputFull, 
	Error_RemoveEmpty, 
	
	
}
