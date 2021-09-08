library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity impressora is
	port (
    SW1,SW2,SW3,SW4 : in  STD_LOGIC;
    x : out STD_LOGIC );
end entity;

architecture arch of impressora is

begin
    process(SW1,SW2,SW3,SW4)
    begin
        if (SW1 = '0' and SW2 = '0') then 
            x <= '1';
        elsif (SW1 = '0' and SW3 = '0') then 
            x <= '1';
        elsif (SW2 = '0' and SW3 = '0') then 
            x <= '1';
        elsif (SW2 = '0' and SW4 = '0') then 
            x <= '1';
        elsif (SW3 = '0' and SW4 = '0') then 
            x <= '1'; 
              
        end if;
    end process;

end architecture;
