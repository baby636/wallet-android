package com.mycelium.wapi.wallet.bip44;

import com.mrd.bitlib.crypto.Bip39;
import com.mrd.bitlib.crypto.RandomSource;
import com.mrd.bitlib.model.Address;
import com.mrd.bitlib.model.AddressType;
import com.mrd.bitlib.model.NetworkParameters;
import com.mycelium.wapi.api.Wapi;
import com.mycelium.WapiLogger;
import com.mycelium.wapi.wallet.*;
import com.mycelium.wapi.wallet.btc.InMemoryWalletManagerBacking;
import com.mycelium.wapi.wallet.btc.WalletManagerBacking;
import com.mycelium.wapi.wallet.btc.bip44.AdditionalHDAccountConfig;
import com.mycelium.wapi.wallet.btc.bip44.HDAccount;

import com.mycelium.wapi.wallet.btc.bip44.UnrelatedHDAccountConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HDAccountTest {
    private static final String MASTER_SEED_WORDS = "oil oil oil oil oil oil oil oil oil oil oil oil";
    private static final String MASTER_SEED_ACCOUNT_0_EXTERNAL_0_ADDRESS = "32LRQQsZt2dAzZq5HADLDEw5Fn8NzLhT35";
    private static final String MASTER_SEED_ACCOUNT_0_INTERNAL_0_ADDRESS = "38irRg7yBNjrpiAFxK2ac6GX1EHhYyjCLy";
    private HDAccount account;

    @Before
    public void setup() throws KeyCipher.InvalidKeyCipher {
        RandomSource fakeRandomSource = mock(RandomSource.class);
        Wapi fakeWapi = mock(Wapi.class);
        WapiLogger fakeLogger = mock(WapiLogger.class);
        when(fakeWapi.getLogger()).thenReturn(fakeLogger);
        LoadingProgressUpdater fakeLoadingProgressUpdater = mock(LoadingProgressUpdater.class);

        WalletManagerBacking backing = new InMemoryWalletManagerBacking();
        SecureKeyValueStore store = new SecureKeyValueStore(backing, fakeRandomSource);
        KeyCipher cipher = AesKeyCipher.defaultKeyCipher();

        // Determine the next BIP44 account index
        Bip39.MasterSeed masterSeed = Bip39.generateSeedFromWordList(MASTER_SEED_WORDS.split(" "), "");

        Map<Currency, CurrencySettings> currenciesSettingsMap = new HashMap<>();
        currenciesSettingsMap.put(Currency.BTC, new BTCSettings(AddressType.P2SH_P2WPKH, new Reference<>(ChangeAddressMode.PRIVACY)));

        WalletManager walletManager = new WalletManager(store, backing, NetworkParameters.productionNetwork, fakeWapi, currenciesSettingsMap);

        walletManager.configureBip32MasterSeed(masterSeed, cipher);

        UUID account1Id = walletManager.createAccounts(new AdditionalHDAccountConfig()).get(0);

        account = (HDAccount) walletManager.getAccount(account1Id);
    }

    /**
     * Test that the first two addresses we generate agree with a specific seed agree with Wallet32
     */
    @Test
    public void addressGenerationTest() throws KeyCipher.InvalidKeyCipher {
        assertEquals(Address.fromString(MASTER_SEED_ACCOUNT_0_EXTERNAL_0_ADDRESS), account.getReceivingAddress().get());
        assertEquals(Address.fromString(MASTER_SEED_ACCOUNT_0_INTERNAL_0_ADDRESS), account.getChangeAddress());
    }
}
