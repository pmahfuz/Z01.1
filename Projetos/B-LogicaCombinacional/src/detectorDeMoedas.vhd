library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity detectorDeMoedas is
	port (
    Q,D,N : in  STD_LOGIC;
    cents     : out STD_LOGIC_VECTOR(4 downto 0));
end entity;

architecture arch of detectorDeMoedas is

begin
    precess(Q,D,N)
    begin 
        if (Q = '1') then 
            cents <= '11001'
        elsif (D = '1') then 
            cents <= '01010'
        elsif (N = '1') then
            cents <= '00101'
        end if;
    end process

end architecture;
