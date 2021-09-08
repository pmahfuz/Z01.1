library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity DMux4Way is
	port ( 
			a:   in  STD_LOGIC;
			sel: in  STD_LOGIC_VECTOR(1 downto 0);
			q0:  out STD_LOGIC;
			q1:  out STD_LOGIC;
			q2:  out STD_LOGIC;
			q3:  out STD_LOGIC);
end entity;

architecture rtl of DMux4Way is
begin
		a <= q0 when (sel = "00") else
		 		q1 when (sel = "01") else
		 		q2 when (sel = "10") else
		 		q3;

end architecture;

