#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int quantityNum;
	int num;
	char dash[] = "------------\n";
	char message[] = "Invalid number was selected!\n";
	char msg[] = "How many numbers: ";
	char msgNumbers[] = "Enter a number: ";
	char fmt[] = "%d";
	char displayPositive[] = "Positive: %d\n";
	char displayNegative[] = "Negative: %d\n";
	char displayZero[] = "Zero: %d\n";
	int posi = 0;
	int nega = 0;
	int zero = 0;
	
	_asm {
		lea eax, msg			//read to eax the value of msg 
		push eax				//put eax in stack
		call printf				//display first message with external i/o
		pop eax					//remove eax from stack
		lea eax, quantityNum	//read to eax the quantityNum variable
		push eax				//put eax in stack
		lea eax, fmt			//read to eax the fmt variable
		push eax				//put eax in stack
		call scanf				//read input from user with external i/o to fmt
		add esp, 8				//remove the first 8 bytes from stack
		
		mov ecx, quantityNum	
		cmp ecx, 0				//compare to 0
		jg loop1				//if greater than go to the loop1 and execute the program
		lea eax, message		//else display message saying 0 was selected
		push eax				
		call printf
		pop eax
		jmp finale	

	loop1:						//label for the loop 
		push ecx				
		lea eax, msgNumbers		//
		push eax				//
		call printf				//this block prints the message enter a number
		lea eax, num			//and asks for input from the user
		push eax				//
		lea eax, fmt			//
		push eax
		call scanf				//read input from user that will be tested 
		mov eax, num			//as positive/negative/zero
		cmp eax, 0				//compares the value input to 0
		jz neutral				//check if its equal to 0 if yes goes to neutral
		jg positive				//check if its bigger than 0 if yes goes to positive
		add nega, 1				//if none of the above checks true then increment one
		jmp endif				//to negative variable
	
	positive:
		add posi, 1				//adds 1 to positive variable 
		jmp endif				//jumps to the end of that n loop time
	neutral:					
		add zero, 1				//adds 1 to zero variable
	endif:
		add esp, 8
		pop eax					//remove eax from stack
		pop ecx					//remove ecx from stack to renew loop counter
		loop loop1				//loop back to the loop1 label
	
	finale:	
		lea eax, dash
		push eax
		call printf
		pop eax
		push posi					
		lea eax, displayPositive	//
		push eax					//	
		call printf					//
		add esp, 8					//
		push nega					//
		lea eax, displayNegative	//This block does the same thing 3 times but one for each
		push eax					//negative, positive and zero
		call printf					//gets the variables of each of them and then prints it
		add esp, 8					//
		push zero					//
		lea eax, displayZero		//
		push eax					//
		call printf					//
		add esp, 8					//
	}
	return 0;
}